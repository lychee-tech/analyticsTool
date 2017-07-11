package com.lychee.analytics.tool.amazon.entity;


import javax.persistence.*;

import java.util.HashSet;
import java.util.Set;



@Entity
@Table(name = "amz_categories")
public class AmzCategoryEntity {
    @Id
    @Column(name="browseId")
    private String browseId;
    private String name;

    @ManyToMany(
            targetEntity=AmzCategoryEntity.class,
            cascade={CascadeType.ALL}
    )
    @JoinTable(
            name="amz_category_parent_child",
            joinColumns=@JoinColumn(name="parent_browseId"),
            inverseJoinColumns = @JoinColumn(name = "child_browseId")

    )
    private Set<AmzCategoryEntity> children = new HashSet<>();



    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "children")
    private Set<AmzCategoryEntity> parents = new HashSet<>();

    public String getBrowseId() {
        return browseId;
    }

    public AmzCategoryEntity setBrowseId(String browseId) {
        this.browseId = browseId;
        return this;
    }


    public String getName() {
        return name;
    }


    public AmzCategoryEntity setName(String name) {
        this.name = name;
        return this;
    }


    public Set<AmzCategoryEntity> getChildren() {
        return children;
    }


    public AmzCategoryEntity setChildren(Set<AmzCategoryEntity> children) {
        this.children = children;
        children.forEach(c->c.parents.add(this));
        return this;
    }


    public AmzCategoryEntity addChild(AmzCategoryEntity child){
        this.children.add(child);
        child.parents.add(this);
        return this;
    }



    public Set<AmzCategoryEntity> getParents() {
        return parents;
    }


    public AmzCategoryEntity setParents(Set<AmzCategoryEntity> parents) {
        this.parents = parents;
        parents.forEach(p->p.children.add(this));
        return this;
    }

    public AmzCategoryEntity addParent(AmzCategoryEntity parent){
        this.parents.add(parent);
        parent.children.add(this);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AmzCategoryEntity entity = (AmzCategoryEntity) o;

        return !(getBrowseId() != null ? !getBrowseId().equals(entity.getBrowseId()) : entity.getBrowseId() != null);

    }

    @Override
    public int hashCode() {
        return getBrowseId() != null ? getBrowseId().hashCode() : 0;
    }
}
