package com.bemym8.repo;

import com.bemym8.models.Comment;
import com.bemym8.models.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByOrderByIdAsc();

    List<Comment> findByContentLike(String content);

}
