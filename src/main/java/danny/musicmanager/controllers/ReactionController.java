package danny.musicmanager.controllers;


import danny.musicmanager.entities.Comment;
import danny.musicmanager.entities.Reaction;
import danny.musicmanager.services.ReactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
@RequestMapping("/reaction")
public class ReactionController {

    private final ReactionService reactionService;


    @PostMapping
    public ResponseEntity<?> addReaction(@RequestBody Reaction reaction) {
        return ResponseEntity.ok(reactionService.addReaction(reaction));
    }

    @GetMapping
    public ResponseEntity<?> getAllReaction() {
        return ResponseEntity.ok(reactionService.findAllReaction());
    }


    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable int id) {

        reactionService.deleteReactionById(id);

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable int id) {
        return ResponseEntity.ok(reactionService.findReactionById(id));

    }

    public ResponseEntity<?> getReactionOnSong(@PathVariable int id){

        return ResponseEntity.ok().body(reactionService.findReactionBySong(id));
    }


    @GetMapping("/user")
    public ResponseEntity<?> getUser() {
        return ResponseEntity.ok().body(reactionService.findUser());
    }

}
