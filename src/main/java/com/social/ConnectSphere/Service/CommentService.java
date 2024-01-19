package com.social.ConnectSphere.Service;

import com.social.ConnectSphere.Exception.ResourceNotFoundException;
import com.social.ConnectSphere.Payload.CommentDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

public interface CommentService {
    CommentDTO addComment(CommentDTO commentDTO, Integer postId, Integer userId) throws ResourceNotFoundException;
    CommentDTO updateComment(CommentDTO commentDTO, Integer commentId) throws ResourceNotFoundException;
    String deleteComment(Integer id) throws ResourceNotFoundException;
    Set<CommentDTO > getAllByPost(Integer id) throws ResourceNotFoundException;
}
