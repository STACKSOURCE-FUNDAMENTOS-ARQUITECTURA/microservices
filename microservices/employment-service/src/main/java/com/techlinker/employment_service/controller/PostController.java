package com.techlinker.employment_service.controller;


import com.techlinker.employment_service.entities.Post;
import com.techlinker.employment_service.service.IPostService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/posts")

public class PostController {
    private final IPostService postService;

    public PostController(IPostService postService) {
        this.postService = postService;

    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<List<Post>> findAllPosts(){
        try {
            List<Post> posts = postService.getAll();
            if(posts.size()>0)
                return new ResponseEntity<>(posts, HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<Post> findById (@PathVariable("id") Long id){
        try {
            Optional<Post> post = postService.getById(id);
            if(post == null)
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            else
                return new ResponseEntity<>(post.get(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/companyId={id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Post> createPost(@PathVariable("id") Long id, @Valid @RequestBody Post post){
        try {
            Post newPost = postService.save(post);
            return ResponseEntity.status(HttpStatus.CREATED).body(newPost);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<Post> updatePost(@PathVariable("id") Long id, @Valid @RequestBody Post post){
        try {
            Optional<Post> postFound = postService.getById(id);
            if(!postFound.isPresent())
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            else {
                post.setId(id);
                postService.save(post);
                return new ResponseEntity<>(post, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<Post> deletePost(@PathVariable("id") Long id){
        try {
            Optional<Post> postFound = postService.getById(id);
            if(!postFound.isPresent())
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            else {
                postService.delete(id);
                return new ResponseEntity<>(HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/company/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Post>> findPostsByCompanyId (@PathVariable("id") Long id){
        try {
            List<Post> posts = postService.findByCompanyId(id);
            if(posts.size()>0)
                return new ResponseEntity<>(posts, HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
