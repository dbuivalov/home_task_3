package org.example;

import java.io.IOException;
import java.util.concurrent.ForkJoinPool;

public class Main {

    public static void main(String[] args) throws IOException {

        String link = "https://skillbox.ru";

        long timer = System.currentTimeMillis();

        System.out.println(new ForkJoinPool().invoke(new ProfessionsGetter(link)));

//        Professions professions = new Professions(link);
//        professions.getLinks().stream().map(professions::getProfessions).forEach(System.out::println);

        long programTime = System.currentTimeMillis() - timer;

        System.out.println("\nВремя исполнения программы: " + programTime);
    }
}