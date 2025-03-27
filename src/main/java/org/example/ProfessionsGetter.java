package org.example;
import java.util.*;
import java.util.concurrent.RecursiveTask;

public class ProfessionsGetter extends RecursiveTask<String> {

    private String link;
    Professions professions = new Professions("https://skillbox.ru");

    public ProfessionsGetter(String link) {
        this.link = link;
        professions.getProfessions(link);
    }

    @Override
    protected String compute() {
        System.out.println("I am working in thread: " + Thread.currentThread().getName());
        String profList = "";
        List<RecursiveTask<String>> taskList = new ArrayList<>();
        for (String childlink : professions.getLinks()) {
            RecursiveTask<String> task = new ProfessionsGetter(childlink);
            task.fork();
            taskList.add(task);
        }
        for (RecursiveTask<String> task : taskList) {
            profList += task.join();
        }
        return profList;
    }
}