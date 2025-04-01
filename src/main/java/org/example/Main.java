package org.example;

import java.util.concurrent.ForkJoinPool;

public class Main {

    public static void main(String[] args) {

        String link = "https://skillbox.ru";

        long timer = System.currentTimeMillis();

        ScillboxPagesGenerator scillboxPagesGenerator = new ScillboxPagesGenerator(link); // сначала создаём узел с "детьми"

        // Каждый "ребёнок" - это отдельный узел с ссылкой.
        // Задача - собрать все ссылки по сайту в одну карту.

        // Печать карты сайта в однопоточном режиме:
//        System.out.println(scillboxPagesGenerator);

        // Печать карты сайта в многопоточном режиме:
        System.out.println(new ForkJoinPool().invoke(new ScillboxPagesProcessor(scillboxPagesGenerator)));

        long programTime = System.currentTimeMillis() - timer;
        System.out.println("\nВремя выполнения программы: " + programTime);
    }
}