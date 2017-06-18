package com.litchi.analytics.tool.amazon.entity;

import javax.persistence.*;

@Entity
@Table(name = "AmzKeywords")
public class AmzKeywordEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String word;

    private String search;



    public Long getId() {
        return id;
    }


    public AmzKeywordEntity setId(Long id) {
        this.id = id;
        return this;
    }


    public String getWord() {
        return word;
    }


    public AmzKeywordEntity setWord(String word) {
        this.word = word;
        return this;
    }


    public String getSearch() {
        return search;
    }


    public AmzKeywordEntity setSearch(String search) {
        this.search = search;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AmzKeywordEntity that = (AmzKeywordEntity) o;

        return !(getWord() != null ? !getWord().equals(that.getWord()) : that.getWord() != null);

    }

    @Override
    public int hashCode() {
        return getWord() != null ? getWord().hashCode() : 0;
    }
}
