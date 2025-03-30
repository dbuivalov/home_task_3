package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

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
    }

    public void generatePagesMap () {
        Element element = document.getElementsByClass("ui-footer-nav-list__links").first();
        Elements elements = element.getElementsByAttribute("href");
        elements.stream().map(e -> new NodeLink(getUrl() + e.attr("href"))).forEach(this::addChildLink);
    }








}
