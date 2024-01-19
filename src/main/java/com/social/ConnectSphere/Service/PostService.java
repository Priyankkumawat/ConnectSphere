package com.social.ConnectSphere.Service;

import com.social.ConnectSphere.Exception.ResourceNotFoundException;
import com.social.ConnectSphere.Payload.PostDTO;
import com.social.ConnectSphere.Payload.PostResponse;

import java.util.List;

public interface PostService {
    PostDTO createPost(PostDTO postDTO, Integer userId) throws ResourceNotFoundException;
    PostDTO updatePost(PostDTO postDTO, Integer postId) throws ResourceNotFoundException;
    void deletePost(Integer postId) throws ResourceNotFoundException;
    PostDTO getPost(Integer postId) throws ResourceNotFoundException;
    PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);
    List<PostDTO> getAllPostByUser(Integer userId) throws ResourceNotFoundException;
    PostDTO getPostDTO(String fileName);
    String getPostImageName(Integer id) throws ResourceNotFoundException;
    List<PostDTO> getPostByKeyword(String keyword);
}