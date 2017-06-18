package com.litchi.analytics.tool.amazon.entity;

import javax.persistence.*;

@Entity
@Table(name = "AmzCategory_parent_child")
public class AmzCategoryRelationshipEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String parentBrowseId;
    private String childBrowseId;



    public Integer getId() {
        return id;
    }


    public void setId(Integer id) {
        this.id = id;
    }


    public String getParentBrowseId() {
        return parentBrowseId;
    }


    public void setParentBrowseId(String parentBrowseId) {
        this.parentBrowseId = parentBrowseId;
    }


    public String getChildBrowseId() {
        return childBrowseId;
    }


    public void setChildBrowseId(String childBrowseId) {
        this.childBrowseId = childBrowseId;
    }
}
