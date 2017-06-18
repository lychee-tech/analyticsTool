package com.litchi.analytics.tool.amazon.model;


public class AmzBrowseNode {
    private String browseId;
    private String name;
    private Boolean isCategoryRoot;

    public AmzBrowseNode(String browseId, String name, Boolean isCategoryRoot){
        this.browseId = browseId;
        this.name =name;
        this.isCategoryRoot = isCategoryRoot;
    }


    public String getBrowseId() {
        return browseId;
    }


    public String getName() {
        return name;
    }


    public Boolean isCategoryRoot() {
        return isCategoryRoot;
    }
}
