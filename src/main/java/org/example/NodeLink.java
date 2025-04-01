package org.example;

import java.util.ArrayList;
import java.util.List;

public class NodeLink {

    private String url;
    private List <NodeLink> childNodes;

    public NodeLink(String url) {
        this.url = url;
        this.childNodes = new ArrayList<>();
    }

    public String getUrl() {
        return url;
    }

    public List<NodeLink> getChildNodes() {
        return childNodes;
    }

    public List <String> getUrls () {
        List<String> urlList = new ArrayList<>();
        childNodes.stream().map(NodeLink::getUrl).forEach(urlList::add);
        return urlList;
    }

    public void addChildNode(NodeLink child) {
        this.childNodes.add(child);
    }

    public void addChildNodes(List<NodeLink> links) {
        this.childNodes.addAll(links);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("\t").append(url).append("\n");
        if (!childNodes.isEmpty()) {
            for (NodeLink childNode : childNodes) {
                builder.append("\t\t").append(childNode.getUrl()).append("\n");
                if(!childNode.getChildNodes().isEmpty()) {
                    childNode.getChildNodes().forEach(ch -> builder.append("\t\t\t").append(ch.getUrl()).append("\n"));
                }
            }
        }
        return builder.toString();
    }
}