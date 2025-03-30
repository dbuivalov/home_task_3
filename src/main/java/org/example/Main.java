package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

public class Main {

    public static void main(String[] args) throws IOException {

        String link = "https://skillbox.ru";

        long timer = System.currentTimeMillis();

//        NodeLink nodeLink = new NodeLink(link);
//
//        System.out.println(new ForkJoinPool().invoke(new NodeProcessor(nodeLink)));

        ScillboxPagesGenerator scillboxPagesGenerator = new ScillboxPagesGenerator(link);
        System.out.println(scillboxPagesGenerator);

//        Document document = null;
//
//        try {
//            document = Jsoup.connect(link).get();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//        NodeLink node = new NodeLink(link);
//
//        List<NodeLink> nodeList = new ArrayList<>();
//
//        Element element = document.getElementsByClass("ui-footer-nav-list__links").first();
//        Elements elements = element.getElementsByAttribute("href");
//
//        elements.stream().map(e -> new NodeLink(link + e.attr("href"))).forEach(nodeList::add);
//
//        node.addChildLinks(nodeList);
//
//        System.out.println(node.printNode());





        long programTime = System.currentTimeMillis() - timer;

        System.out.println("\nВремя исполнения программы: " + programTime);
    }
}
