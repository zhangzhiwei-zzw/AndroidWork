package com.it.servic;

import com.it.model.Comment;

import java.util.List;

public interface CommentService {

    List<Comment> getAllComment();
    Integer addComment(Comment comment);
}
