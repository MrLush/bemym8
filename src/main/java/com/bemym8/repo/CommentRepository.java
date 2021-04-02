package com.bemym8.repo;

import com.bemym8.models.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Project, Long> {
    List<Project> findAllByOrderByIdAsc();

    List<Project> findByBodyLike(String body);

}
