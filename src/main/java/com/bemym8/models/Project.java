package com.bemym8.models;

import javax.persistence.*;

@Entity
@Table
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    private long authorId;

    private String title;

    private String shortDescription;

    //TEXT because we want that datatype in postgres
    @Column(columnDefinition = "TEXT")
    private String body;

    public long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(long authorId) {
        this.authorId = authorId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
