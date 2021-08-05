package danny.musicmanager.controllers;


import danny.musicmanager.entities.User;
import danny.musicmanager.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<?> newUser(@Valid @RequestBody User user){

        System.out.println("controller");
        return ResponseEntity.ok().body(userService.createUser(user));
    }

    @GetMapping
    public ResponseEntity<?> getAllUsers(){

        return ResponseEntity.ok().body(userService.findAllUsers());

    }


    @PutMapping("/update")
    public ResponseEntity<?> update(int id,@RequestBody User user){
        return ResponseEntity.ok(userService.updateUser(id,user));

    }

    @GetMapping("/get/{occupation}")
    public ResponseEntity<?> getUserByOccupation(@PathVariable String occupation){

        return ResponseEntity.ok(userService.findUserByOccupation(occupation));
    }


    @GetMapping("/music/{id}")
    public ResponseEntity<?> getUserByMusicId(@PathVariable int id){
        return ResponseEntity.ok(userService.findByMusicId(id));
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable int id){
        return ResponseEntity.ok(userService.findUserById(id));

    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable int id){
        userService.deleteUser(id);

    }


}
