package com.litchi.analytics.tool.amazon.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "AmzCategories")
public class AmzCategoryEntity {
    @Id
    private String browseId;
    private String name;


    public String getBrowseId() {
        return browseId;
    }

    public void setBrowseId(String browseId) {
        this.browseId = browseId;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }
}
