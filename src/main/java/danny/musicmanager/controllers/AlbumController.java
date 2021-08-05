package danny.musicmanager.controllers;


import danny.musicmanager.entities.Album;
import danny.musicmanager.entities.Comment;
import danny.musicmanager.entities.Reaction;
import danny.musicmanager.services.AlbumService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/album")
public class AlbumController {


    private final AlbumService albumService;

    @PostMapping
    public ResponseEntity<?> addAlbum(@RequestBody Album album){
        return ResponseEntity.ok(albumService.saveAlbum(album));
    }

    @GetMapping
    public ResponseEntity<?> getAllAlbum() {
        return ResponseEntity.ok(albumService.findAllAlbum());
    }


    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable int id){

        albumService.deleteAlbum(id);

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable int id){
        return ResponseEntity.ok(albumService.findAlbumById(id));

    }

    @PostMapping("/comment/{id}")
    public ResponseEntity<?> commentOnAlbum(@PathVariable int id,@RequestBody Comment comment){

        return ResponseEntity.ok().body(albumService.CommentOnAlbum(id,comment));

    }
    @GetMapping("/album/{id}")
    public ResponseEntity<?> reactToAlbum(@PathVariable int id, @RequestBody Reaction reaction) {

        return ResponseEntity.ok().body(albumService.reactionOnAlbum(id,reaction));

    }

    @GetMapping("/user")
    public ResponseEntity<?> getTheUserThatAddedAlbum(){
        return ResponseEntity.ok().body(albumService.findUser());
    }

}
