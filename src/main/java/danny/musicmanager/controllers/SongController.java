package danny.musicmanager.controllers;


import danny.musicmanager.entities.Comment;
import danny.musicmanager.entities.Reaction;
import danny.musicmanager.entities.Song;
import danny.musicmanager.services.SongService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@RestController
@RequestMapping("/song")
public class SongController {

    private final SongService songService;



    @PostMapping
    public ResponseEntity<?> addSongs(@RequestBody Song song){

        return ResponseEntity.ok().body(songService.addSong(song));

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getSong(@PathVariable int id) {
        return ResponseEntity.ok(songService.findSongById(id));
    }


//    @DeleteMapping("{id}")
//    public ResponseEntity<?> deleteMusic(@PathVariable long id){
//        return ResponseEntity.ok(musicService.deleteMusic(id));
//    }


    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable int id){
        songService.deleteSongById(id);
    }


    @DeleteMapping("/outdated/{id}")
    public void deleteOutdatedSongs(@PathVariable int id){
        songService.deleteOutDatedSongById(id);
    }

    @GetMapping("/user")
    private ResponseEntity<?> getByUser (){
        return ResponseEntity.ok(songService.findUser());

    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllSong(){
        return ResponseEntity.ok(songService.findAllSong());

    }

    @PostMapping("/comment/{id}")
    public ResponseEntity<?> commentOnSong(@PathVariable int id, @RequestBody Comment comment){

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(songService.commentOnSong(id, comment));


    }

    @PostMapping("/reaction/{id}")
    public ResponseEntity<?> reactToSong(@PathVariable int id, @RequestBody Reaction reaction){

        return ResponseEntity.ok().body(songService.reactionOnSong(id,reaction));


    }




}
