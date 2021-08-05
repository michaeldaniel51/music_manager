package danny.musicmanager.controllers;


import danny.musicmanager.entities.Album;
import danny.musicmanager.entities.Comment;
import danny.musicmanager.services.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;


    
    @PostMapping
    public ResponseEntity<?> addComment(@RequestBody Comment comment) {
        return ResponseEntity.ok(commentService.addComment(comment));
    }

    @GetMapping
    public ResponseEntity<?> getAllComment() {
        return ResponseEntity.ok(commentService.findAllComment());
    }


    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable int id) {

        commentService.deleteComment(id);

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable int id) {
        return ResponseEntity.ok(commentService.findById(id));

    }


    @GetMapping("/song/{id}")
    public ResponseEntity<?> getCommentOnSong(@PathVariable int id) {

        return ResponseEntity.ok().body(commentService.findCommentBySong(id));

    }

    @GetMapping("/album/{id}")
    public ResponseEntity<?> getCommentOnAlbum(@PathVariable int id) {

        return ResponseEntity.ok().body(commentService.findCommentOnAlbum(id));
    }

    @GetMapping("/user")
    public ResponseEntity<?> getUser() {
            return ResponseEntity.ok().body(commentService.findUser());
        }

}
