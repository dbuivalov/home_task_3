package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        String link = "https://skillbox.ru";

        String link2 = "https://skillbox.ru/code/";

        List<String> links = new ArrayList<>();

        try {
            Document doc = Jsoup.connect(link).get();

            Document doc2 = Jsoup.connect(link2).get();




            Element element = doc.getElementsByClass("ui-footer-nav-list__links").first();

            Elements elements2 = element.getElementsByAttribute("href");

            elements2.forEach(e -> links.add(link + e.attr("href")));




            Elements elements = doc2.getElementsByClass("ui-product-card-main__wrap");

            elements.forEach(e -> System.out.println(e.attr("href")));







        } catch (IOException e) {
            throw new RuntimeException(e);
        }

//        links.forEach(System.out::println);
















    }
}