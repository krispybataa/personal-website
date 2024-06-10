package com.example.personal_website.hobbies;

import com.example.personal_website.users.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class HobbiesService {
    private final HobbiesRepository hobbiesRepository;
    @Autowired
    public HobbiesService(HobbiesRepository hobbiesRepository){this.hobbiesRepository = hobbiesRepository;}

    public List<Hobbies> getAllHobbies() {
        return hobbiesRepository.findAll();
    }
    public Optional<Hobbies> getHobbiesByName(String name) {return hobbiesRepository.findHobbiesByName(name);}
    public List<Hobbies> getHobbiesByUser(Users users) {return hobbiesRepository.findHobbiesByUsers(users);}
//    public List<Hobbies> getHobbiesByUserId(Long usersId) {return hobbiesRepository.findHobbiesByUserId(usersId);}

    public void addHobby(Hobbies hobbies) {
        Optional<Hobbies> hobbiesOptional = hobbiesRepository.findHobbiesByName(hobbies.getName());
        if(hobbiesOptional.isPresent()){
            throw new IllegalStateException("Hobby already exists.");
        }
        hobbiesRepository.save(hobbies);
    }

    public void updateHobbies(Long hobbiesId,
                              String name,
                              String description,
                              String media) {
        Hobbies hobbies = hobbiesRepository.findById(hobbiesId).orElseThrow(()
                -> new IllegalStateException("Hobby ID: " + hobbiesId + " already exists."));

        //Update recorded hobby name entry
        if(name != null && !name.isEmpty() && !Objects.equals(hobbies.getName(), name)){
            Optional<Hobbies> hobbiesOptional = hobbiesRepository.findHobbiesByName(name);
            if(hobbiesOptional.isPresent()){
                throw new IllegalStateException("Hobby: " + name + " is already on record.");
            }
            hobbies.setName(name);
        }
        //Update description entry
        if (description != null && !description.isEmpty()) {
            hobbies.setDescription(description);
        }
        //Update media entries
        if (media != null && !media.isEmpty()) {
            hobbies.setMedia(media);
        }

        hobbiesRepository.save(hobbies);
    }

    public void deleteHobbies(Long hobbiesId) {
        boolean exists = hobbiesRepository.existsById(hobbiesId);
        if(!exists){
            throw new IllegalStateException("Hobby ID: " + hobbiesId + " does not exist.");
        }
        hobbiesRepository.deleteById(hobbiesId);
    }
}
