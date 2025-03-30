package org.example;

import java.util.ArrayList;
import java.util.List;

public class NodeLink {

    private String url;
    private List <NodeLink> childLinks;

    public NodeLink(String url) {
        this.url = url;
        this.childLinks = new ArrayList<>();
    }

    public String getUrl() {
        return url;
    }

    public List<NodeLink> getChildLinks() {
        return childLinks;
    }

    public void addChildLink(NodeLink child) {
        this.childLinks.add(child);
    }

    public void addChildLinks(List<NodeLink> links) {
        this.childLinks.addAll(links);
    }

    public String printNode() {
        StringBuilder builder = new StringBuilder();
        builder.append(url + "\n");
        childLinks.forEach(s -> builder.append("        " + s + "\n"));
        return builder.toString();
    }

    @Override
    public String toString() {
        return childLinks.isEmpty() ? this.url : printNode();
    }
}