package com.social.ConnectSphere.Service.ServiceImpl;

import com.social.ConnectSphere.Exception.ResourceNotFoundException;
import com.social.ConnectSphere.Model.Comment;
import com.social.ConnectSphere.Model.Post;
import com.social.ConnectSphere.Model.User;
import com.social.ConnectSphere.Payload.CommentDTO;
import com.social.ConnectSphere.Repository.CommentRepository;
import com.social.ConnectSphere.Repository.PostRepository;
import com.social.ConnectSphere.Repository.UserRepository;
import com.social.ConnectSphere.Service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public CommentDTO addComment(CommentDTO commentDTO, Integer postId, Integer userId) throws ResourceNotFoundException {
        Comment comment = commentFromDTO(commentDTO);

        Post post = postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","Id",postId.toString()));
        User user = userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","Id",userId.toString()));

        comment.setUser(user);
        comment.setPost(post);

        Comment savedComment = commentRepository.save(comment);

        user.getCommentSet().add(savedComment);
        post.getCommentSet().add(savedComment);

        userRepository.save(user);
        postRepository.save(post);

        return commentDTOFromComment(savedComment);
    }

    @Override
    public CommentDTO updateComment(CommentDTO commentDTO, Integer commentId) throws ResourceNotFoundException {
        Comment comment = commentRepository.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment","Id",commentId.toString()));
        comment.setText(commentDTO.getText());
        comment = commentRepository.save(comment);
        return commentDTOFromComment(comment);
    }

    @Override
    public String deleteComment(Integer id) throws ResourceNotFoundException {
        Comment comment = commentRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Comment","Id",id.toString()));
        commentRepository.delete(comment);
        return "success";
    }

    @Override
    public Set<CommentDTO> getAllByPost(Integer id) throws ResourceNotFoundException {
        Post post = postRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Post","Id",id.toString()));
        Set<Comment> commentList = post.getCommentSet();
        Set<CommentDTO> commentDTOS = commentList.stream().map(this::commentDTOFromComment).collect(Collectors.toSet());
        return commentDTOS;
    }

    private Comment commentFromDTO(CommentDTO commentDTO){
        return modelMapper.map(commentDTO,Comment.class);
    }
    private CommentDTO commentDTOFromComment(Comment comment){
        return modelMapper.map(comment,CommentDTO.class);
    }
}
