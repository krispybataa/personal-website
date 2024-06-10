package com.example.personal_website.hobbies;

import com.example.personal_website.users.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/hobbies")
public class HobbiesController {
    private final HobbiesService hobbiesService;
    @Autowired
    public HobbiesController(HobbiesService hobbiesService){this.hobbiesService = hobbiesService;}

    @GetMapping
    public List<Hobbies> getAllHobbies(){return hobbiesService.getAllHobbies();}
    @GetMapping(path = "/name/{name}")
    public Optional<Hobbies> getHobbiesByName(String name){return hobbiesService.getHobbiesByName(name);}
    @GetMapping(path = "/users/{users}")
    public List<Hobbies> getHobbiesByUser(Users users){return hobbiesService.getHobbiesByUser(users);}

//    @GetMapping(path = "/users/{usersId}")
//    public List<Hobbies> getHobbiesByUser(Long usersId){return hobbiesService.getHobbiesByUserId(usersId);}
    @PostMapping
    public void registerHobby(@RequestBody Hobbies hobbies){hobbiesService.addHobby(hobbies);}
    @PutMapping
    public void updateHobby(
            @PathVariable("hobbiesId") Long hobbiesId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) List<String> media
    )
    {
        hobbiesService.updateHobbies(hobbiesId, name, description, media);
    }
    @DeleteMapping(path = "{hobbiesId}")
    public void deleteHobby(@PathVariable("hobbiesId") Long hobbiesId){hobbiesService.deleteHobbies(hobbiesId);}
}
