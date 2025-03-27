package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.*;

public class Professions {

    String link;
    Document doc;
    StringBuilder builder;

    public Professions(String link) {
        this.link = link;
        this.builder = new StringBuilder();
        try {
            this.doc = Jsoup.connect(link).get();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Set<String> getLinks () {
        Set<String> linksList = new LinkedHashSet<>();
        Element element = doc.getElementsByClass("ui-footer-nav-list__links").first();
        Elements elementsOut = element.getElementsByAttribute("href");
        elementsOut.forEach(e -> linksList.add(link + e.attr("href")));
        return linksList;
    }

    public String getProfessions(String link) {
        System.out.println("I am working in thread: " + Thread.currentThread().getName());
        StringBuilder builder = new StringBuilder();
        Map<String, List<String>> professionsMap = new LinkedHashMap<>();
        List<String> professions = new ArrayList<>();
        try {
            Document docLinkOut = Jsoup.connect(link).get();
            Elements elementsIn = docLinkOut.getElementsByClass("ui-product-card-main__wrap");
            elementsIn.forEach(el -> {
                System.out.println("I am working in thread: " + Thread.currentThread().getName());
                if (el.attr("href").contains("https://skillbox.ru")){
                    professions.add(el.attr("href"));
                } else {
                    professions.add("https://skillbox.ru" + el.attr("href"));
                }});
            professionsMap.put(link, professions);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        for(String key : professionsMap.keySet()) {
            builder.append(key + "\n");
            professionsMap.get(key).forEach(s -> builder.append("        " + s + "\n"));
        }
        return builder.toString();
    }
}