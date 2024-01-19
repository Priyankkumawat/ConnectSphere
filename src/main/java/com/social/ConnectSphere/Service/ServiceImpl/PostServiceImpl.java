package com.social.ConnectSphere.Service.ServiceImpl;

import com.social.ConnectSphere.Exception.ResourceNotFoundException;
import com.social.ConnectSphere.Model.Post;
import com.social.ConnectSphere.Model.User;
import com.social.ConnectSphere.Payload.PostDTO;
import com.social.ConnectSphere.Payload.PostResponse;
import com.social.ConnectSphere.Repository.PostRepository;
import com.social.ConnectSphere.Repository.UserRepository;
import com.social.ConnectSphere.Service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public PostDTO createPost(PostDTO postDTO, Integer userId) throws ResourceNotFoundException {
        User user = userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","Id",userId.toString()));
        Post post = postFromDTO(postDTO);
        post.setAddedDate(new Date());
        post.setImageName("default.png");
        post.setUser(user);
        post = postRepository.save(post);
        user.getPostList().add(post);
        userRepository.save(user);
        return postDTOFromPost(post);
    }

    @Override
    public PostDTO updatePost(PostDTO postDTO, Integer postId) throws ResourceNotFoundException {
        Post post = postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","Id",postId.toString()));
        if(postDTO.content != null) post.setContent(postDTO.content);
        if(postDTO.imageName != null) post.setImageName(postDTO.imageName);
        Post savedPost = postRepository.save(post);
        return postDTOFromPost(savedPost);
    }

    @Override
    public void deletePost(Integer postId) throws ResourceNotFoundException {
        Post post = postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","Id",postId.toString()));
        postRepository.delete(post);
    }

    @Override
    public PostDTO getPost(Integer postId) throws ResourceNotFoundException {
        Post post = postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","Id",postId.toString()));
        return postDTOFromPost(post);
    }

    @Override
    public PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir){
        Sort sort=Sort.by(sortBy).ascending();
        if(sortDir.equalsIgnoreCase("desc")){
            sort = Sort.by(sortBy).descending();
        }

        Pageable p = (Pageable) PageRequest.of(pageNumber,pageSize, sort);
        Page<Post> postPage = postRepository.findAll(p);
        List<Post> posts = postPage.getContent();
        List<PostDTO> postDTOS = posts.stream().map(this::postDTOFromPost).collect(Collectors.toList());

        PostResponse postResponse = new PostResponse();
        postResponse.setContent(postDTOS);
        postResponse.setPageNumber(pageNumber);
        postResponse.setPageSize(pageSize);
        postResponse.setTotalPages(postPage.getTotalPages());
        postResponse.setTotalElements(postPage.getNumberOfElements());
        postResponse.setLastPage(postPage.isLast());
        return postResponse;
    }

    @Override
    public List<PostDTO> getAllPostByUser(Integer userId) throws ResourceNotFoundException {
        User user = userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","Id",userId.toString()));
        List<Post> posts = user.getPostList();
        return posts.stream().map(this::postDTOFromPost).collect(Collectors.toList());
    }

    @Override
    public PostDTO getPostDTO(String fileName) {
        PostDTO postDTO=new PostDTO();
        postDTO.setImageName(fileName);
        return postDTO;
    }

    @Override
    public String getPostImageName(Integer id) throws ResourceNotFoundException {
        Post post = postRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Post","Id",id.toString()));
        return post.getImageName();
    }

    @Override
    public List<PostDTO> getPostByKeyword(String keyword) {
        List<Post> posts = postRepository.findByContent(keyword);
        List<PostDTO> postDTOS = posts.stream().map(this::postDTOFromPost).collect(Collectors.toList());
        return postDTOS;
    }

    private Post postFromDTO(PostDTO postDTO){
        return modelMapper.map(postDTO, Post.class);
    }
    private PostDTO postDTOFromPost(Post post){
        return modelMapper.map(post,PostDTO.class);
    }
}