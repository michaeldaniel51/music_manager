package danny.musicmanager.controllers;


import danny.musicmanager.entities.Comment;
import danny.musicmanager.entities.FeedBack;
import danny.musicmanager.services.FeedBackService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/feedback")
public class FeedBackController {

    private final FeedBackService feedBackService;

    @PostMapping
    public ResponseEntity<?> addFeedBack(@RequestBody FeedBack feedBack) {
        return ResponseEntity.ok(feedBackService.addFeedBack(feedBack));
    }

    @GetMapping
    public ResponseEntity<?> getAllFeedBackOnApp() {
        return ResponseEntity.ok(feedBackService.findAllFeedBack());
    }


    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable int id) {

        feedBackService.deleteFeedBackById(id);

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable int id) {
        return ResponseEntity.ok(feedBackService.findReactionById(id));

    }

    @GetMapping("/user")
    public ResponseEntity<?> getUser() {
        return ResponseEntity.ok().body(feedBackService.findUser());
    }

}
