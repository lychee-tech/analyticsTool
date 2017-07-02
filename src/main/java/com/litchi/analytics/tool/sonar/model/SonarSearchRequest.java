package com.litchi.analytics.tool.sonar.model;


public class SonarSearchRequest {
    String country;
    String keyword;

    public String getCountry() {
        return country;
    }

    public SonarSearchRequest setCountry(String country) {
        this.country = country;
        return this;
    }

    public String getKeyword() {
        return keyword;
    }

    public SonarSearchRequest setKeyword(String keyword) {
        this.keyword = keyword;
        return this;
    }
}
