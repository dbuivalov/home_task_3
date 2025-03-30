package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class NodeProcessor extends RecursiveTask <NodeLink>{

    private NodeLink node;
    private Document doc;

    public NodeProcessor(NodeLink node) {
        this.node = node;
        try {
            this.doc = Jsoup.connect(node.getUrl()).get();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected NodeLink compute() {
//        Elements elements = this.doc.getElementsByClass("ui-product-card-main__wrap");
//        elements.stream().
//                map(e -> {
//                    NodeLink child;
//                    if (e.attr("href").contains("https://skillbox.ru")){
//                        child = new NodeLink(e.attr("href"));
//                    } else {
//                        child = new NodeLink((URL).concat(e.attr("href")));
//                    }
//                    return child;
//                }).forEach(childNode -> node.addChildLink(childNode));

        Element element = doc.getElementsByClass("ui-footer-nav-list__links").first();
        element.stream().map(e -> {
            String newLink = node.getUrl() + e.attr("href");
            return new NodeLink(newLink);}).forEach(node::addChildLink);

        List <NodeProcessor> taskList = new ArrayList<>();
        for (NodeLink child : node.getChildLinks()) {
           NodeProcessor task = new NodeProcessor(child);
            task.fork();
            taskList.add(task);
        }
        for (NodeProcessor task: taskList) {
            node.addChildLink(task.join());
        }
        return node;
    }
}