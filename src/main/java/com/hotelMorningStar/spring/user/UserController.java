package com.hotelMorningStar.spring.user;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.source.InvalidConfigurationPropertyValueException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController

@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {
	
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private UserUtils userUtils;

	@PostMapping("/addUser")
	public String saveUser(@RequestBody User user){
		repository.save(user);
		return "Added User with id : "+user.getId();
	}

	
	@PutMapping("/updateWallet/{id}")
    public ResponseEntity<User> updateWallet(@PathVariable(value = "id") String userId, @RequestBody User userDetails) throws InvalidConfigurationPropertyValueException {
         User user = repository.findById(userId)
          .orElseThrow(() -> new InvalidConfigurationPropertyValueException("User not found on :: "+ userId, userDetails, userId));
         
         
        user.setWallet(userDetails.getWallet());
        final User updatedUser = repository.save(user);
        userUtils.sendMail(Integer.toString(userDetails.getWallet()), userDetails.getId());
        return ResponseEntity.ok(updatedUser);
   }

	@PutMapping("/updatePassword/{id}")
    public ResponseEntity<User> updatePassword(@PathVariable(value = "id") String userId, @RequestBody User userDetails) throws InvalidConfigurationPropertyValueException {
         User user = repository.findById(userId)
          .orElseThrow(() -> new InvalidConfigurationPropertyValueException("User not found on :: "+ userId, userDetails, userId));

        user.setPassword(userDetails.getPassword());
        final User updatedUser = repository.save(user);
        userUtils.sendPasswordUpdate(userDetails.getId());
        return ResponseEntity.ok(updatedUser);
   }

	@GetMapping("/findAllUsers")
	public List<User> getUsers(){
		return repository.findAll();
	}

	@GetMapping("/findAllUsers/{id}")
	public Optional<User> getUser(@PathVariable String id){
		return repository.findById(id);
	}

	@DeleteMapping("/deleteUser/{id}")
	public String deleteRoom(@PathVariable String id){
		repository.deleteById(id);
		return "User deleted with id : "+id;
	}

}
