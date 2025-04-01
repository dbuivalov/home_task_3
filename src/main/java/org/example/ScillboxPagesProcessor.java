package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class ScillboxPagesProcessor extends RecursiveTask<String> {

    private NodeLink node;

    public ScillboxPagesProcessor(NodeLink node) {
        this.node = node;
    }

    @Override
    protected String compute() {
        System.out.println("I am working in thread: " + Thread.currentThread().getName());

        StringBuilder nodeList = new StringBuilder();
        nodeList.append(node.getUrl()).append("\n");

        List <ScillboxPagesProcessor> taskList1 = new ArrayList<>();
        List <ScillboxPagesProcessor> taskList2 = new ArrayList<>();

        for (NodeLink child : node.getChildNodes()) {
            ScillboxPagesProcessor task = new ScillboxPagesProcessor(new NodeLink(child.getUrl()));
            task.fork(); // вот так будем ответвлять задачу и отдавать её пулу потоков.
            taskList1.add(task);
            for (NodeLink child2 : child.getChildNodes()) {
                ScillboxPagesProcessor task2 = new ScillboxPagesProcessor(new NodeLink(child2.getUrl()));
                task2.fork(); // вот так будем ответвлять задачу и отдавать её пулу потоков.
                taskList2.add(task2);
            }
        }

        for (ScillboxPagesProcessor task1 : taskList1) {
            System.out.println("I am working in thread: " + Thread.currentThread().getName());
            nodeList.append("\t").append(task1.join());
            for (ScillboxPagesProcessor task2 : taskList2) {
                nodeList.append("\t\t").append(task2.join());
            }
        }
        return nodeList.toString();
    }
}