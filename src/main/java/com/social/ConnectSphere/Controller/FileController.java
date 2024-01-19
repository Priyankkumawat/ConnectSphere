package com.social.ConnectSphere.Controller;

import com.social.ConnectSphere.Exception.FileResponse;
import com.social.ConnectSphere.Exception.ResourceNotFoundException;
import com.social.ConnectSphere.Service.FileService;
import com.social.ConnectSphere.Service.PostService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/file")
public class FileController {
    @Autowired
    private FileService fileService;
    @Autowired
    private PostService postService;
    @Value("${project.image}")
    private String path;
    @PostMapping("/upload/{postId}")
    public ResponseEntity<?> uploadImage(@RequestParam("image")MultipartFile image,
                                         @PathVariable Integer postId){
        String fileName = null;
        try{
            fileName = fileService.uploadImage(path,image);
            postService.updatePost(postService.getPostDTO(fileName),postId);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(new FileResponse(null, "Not uploaded"), HttpStatus.OK);
        }
        catch (ResourceNotFoundException e){
            return new ResponseEntity<>("Not added", HttpStatus.OK);
        }
        return new ResponseEntity<>(new FileResponse(fileName,"uploaded"), HttpStatus.OK);
    }

    @GetMapping("/imageGet/{postId}")
    public void getImage(@PathVariable Integer postId, HttpServletResponse response) throws IOException, ResourceNotFoundException {
        String imageName = postService.getPostImageName(postId);
        System.out.println(imageName);
        InputStream resource = fileService.getResource(path, imageName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource,response.getOutputStream());
    }
}