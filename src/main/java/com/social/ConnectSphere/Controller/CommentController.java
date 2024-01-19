package com.social.ConnectSphere.Controller;

import com.social.ConnectSphere.Exception.ResourceNotFoundException;
import com.social.ConnectSphere.Model.Comment;
import com.social.ConnectSphere.Payload.CommentDTO;
import com.social.ConnectSphere.Repository.CommentRepository;
import com.social.ConnectSphere.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private CommentRepository commentRepository;

    @PostMapping("/add/{postId}/{userId}")
    public ResponseEntity<?> add(@RequestBody CommentDTO commentDTO, @PathVariable Integer postId, @PathVariable Integer userId) throws ResourceNotFoundException {
        CommentDTO commentDTO1 = commentService.addComment(commentDTO,postId,userId);
        return new ResponseEntity<>(commentDTO1, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody CommentDTO commentDTO, @RequestParam Integer commentId) throws ResourceNotFoundException {
        CommentDTO commentDTO1 = commentService.updateComment(commentDTO,commentId);
        return new ResponseEntity<>(commentDTO1,HttpStatus.OK);
    }
    @GetMapping("/getByPost/{postId}")
    public ResponseEntity<?> getAllByPost(@PathVariable Integer id) throws ResourceNotFoundException {
        Set<CommentDTO> commentDTOS = commentService.getAllByPost(id);
        return new ResponseEntity<>(commentDTOS, HttpStatus.CONTINUE);
    }
    @DeleteMapping("/delete/{commentId}")
    public ResponseEntity<?> delete(@PathVariable Integer id) throws ResourceNotFoundException{
        return new ResponseEntity<>(commentService.deleteComment(id), HttpStatus.GONE);
    }
}
