package com.it.servic.impl;

import com.it.mapper.CommentMapper;
import com.it.mapper.UserMapper;
import com.it.model.Comment;
import com.it.servic.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public List<Comment> getAllComment() {
        return commentMapper.getAllComment();
    }

    @Override
    public Integer addComment(Comment comment) {
        return commentMapper.addComment(comment);
    }
}
