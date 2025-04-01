package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class ScillboxPagesGenerator extends NodeLink {

    Document document;

    public ScillboxPagesGenerator(String url) {
        super(url);
        try {
            this.document = Jsoup.connect(url).get();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        generatePagesMap();
        getChildNodes().forEach(this::generatePages);
    }

    public void generatePagesMap () {
        Element element = document.getElementsByClass("ui-footer-nav-list__links").first();
        Elements elements = element.getElementsByAttribute("href");
        elements.stream().map(e -> new NodeLink(getUrl() + e.attr("href"))).forEach(this::addChildNode);
    }

    public void generatePages (NodeLink node) {
        Document document;
        try {
            document = Jsoup.connect(node.getUrl()).get();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Elements elements = document.getElementsByClass("ui-product-card-main__wrap");
        List<NodeLink> nodesList = elements.stream().
                map(e -> {
                    NodeLink child;
                    if (e.attr("href").contains("https://skillbox.ru")) {
                        child = new NodeLink(e.attr("href"));
                    } else {
                        child = new NodeLink(("https://skillbox.ru").concat(e.attr("href")));
                    }
                    return child;
                }).collect(Collectors.toList());
        node.addChildNodes(nodesList);
    }
}