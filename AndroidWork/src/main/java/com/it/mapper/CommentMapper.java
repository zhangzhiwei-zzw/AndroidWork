package com.it.mapper;


import com.it.model.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {

    List<Comment> getAllComment();

    Integer addComment(Comment comment);
}