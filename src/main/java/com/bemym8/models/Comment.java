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
}
