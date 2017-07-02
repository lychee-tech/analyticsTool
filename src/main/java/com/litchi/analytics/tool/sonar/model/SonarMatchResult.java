package com.litchi.analytics.tool.sonar.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SonarMatchResult {
    private long size;
    private List<SonarMatchItem>  listWithScore;

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }


    public List<SonarMatchItem> getListWithScore() {
        return listWithScore;
    }


    public void setListWithScore(List<SonarMatchItem> listWithScore) {
        this.listWithScore = listWithScore;
    }
}
