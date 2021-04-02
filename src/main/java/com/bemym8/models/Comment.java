package com.bemym8.models;

import javax.persistence.*;

@Entity
@Table
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    //TEXT because we want that datatype in postgres
    @Column(columnDefinition = "TEXT")
    private String content;
    private long user_id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "project_id", referencedColumnName = "id")
    private Project projectBO;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public Project getProjectBO() {
        return projectBO;
    }

    public void setProjectBO(Project projectBO) {
        this.projectBO = projectBO;
    }
}
