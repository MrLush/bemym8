package com.bemym8.models;

import javax.persistence.*;
import java.util.Date;

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
    private String user_firstName;
    private String user_lastName;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "project_id", referencedColumnName = "id")
    private Project projectBO;

    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date createdTimestamp;

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

    public Date getCreatedTimestamp() {
        return createdTimestamp;
    }

    public String getUser_firstName() {
        return user_firstName;
    }

    public void setUser_firstName(String user_firstName) {
        this.user_firstName = user_firstName;
    }

    public String getUser_lastName() {
        return user_lastName;
    }

    public void setUser_lastName(String user_lastName) {
        this.user_lastName = user_lastName;
    }

    public void setCreatedTimestamp(Date createdTimestamp) {
        this.createdTimestamp = createdTimestamp;
    }
}
