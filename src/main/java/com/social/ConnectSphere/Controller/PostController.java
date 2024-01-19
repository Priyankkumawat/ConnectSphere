package com.social.ConnectSphere.Controller;

import com.social.ConnectSphere.Exception.ResourceNotFoundException;
import com.social.ConnectSphere.Payload.PostDTO;
import com.social.ConnectSphere.Payload.PostResponse;
import com.social.ConnectSphere.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {
    @Autowired
    private PostService postService;

    @PostMapping("/add/{userId}")
    public ResponseEntity<?> addPost( @RequestBody PostDTO postDTO, @PathVariable Integer userId){
        try{
            PostDTO postDTO1 = postService.createPost(postDTO, userId);
            return new ResponseEntity<>(postDTO1, HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update/{postId}")
    public ResponseEntity<?> updatePost(@RequestBody PostDTO postDTO,@PathVariable Integer postId) throws ResourceNotFoundException {
        PostDTO postDTO1 = postService.updatePost(postDTO,postId);
        return new ResponseEntity<>(postDTO1,HttpStatus.OK);
    }

    @GetMapping("/get/{postId}")
    public ResponseEntity<?> getPost(@PathVariable Integer postId) throws ResourceNotFoundException {
        PostDTO postDTO = postService.getPost(postId);
        return new ResponseEntity<>(postDTO,HttpStatus.FOUND);
    }
    @GetMapping("/getAll")
    public ResponseEntity<?> getAllPost(
            @RequestParam(value = "pageNumber", defaultValue = "2", required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "2", required = false) Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = "Id", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir
    ) {
        PostResponse postResponse = postService.getAllPost(pageNumber,pageSize,sortBy, sortDir);
        return new ResponseEntity<>(postResponse,HttpStatus.FOUND);
    }
    @GetMapping("/getAllByUser/{userId}")
    public ResponseEntity<?> getAllPostByUser(@PathVariable Integer userId) throws ResourceNotFoundException {
        List<PostDTO> postDTOList = postService.getAllPostByUser(userId);
        return new ResponseEntity<>(postDTOList,HttpStatus.FOUND);
    }

    //search
    @GetMapping("/search/{keyword}")
    public ResponseEntity<?> getPostByKeyword(@PathVariable String keyword){
        List<PostDTO> postDTOS = postService.getPostByKeyword("%" + keyword + "%");
        return new ResponseEntity<>(postDTOS, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{postId}")
    public ResponseEntity<?> deletePost(@PathVariable Integer postId) throws ResourceNotFoundException {
        postService.deletePost(postId);
        return new ResponseEntity<>(HttpStatus.GONE);
    }
}