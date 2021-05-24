package com.hotelMorningStar.spring.room;

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

import com.hotelMorningStar.spring.room.Room;

@RestController

@CrossOrigin(origins = "*", allowedHeaders = "*")
public class RoomController {
	
	@Autowired
	private RoomRepository repository;

	@PostMapping("/addRoom")
	public String saveRoom(@RequestBody Room room){
		repository.save(room);
		return "Added room with id : "+room.getId();
	}

	@PutMapping("/updateRoom/{id}")
    public ResponseEntity<Room> updateRoom(
    @PathVariable(value = "id") String roomId,
    @RequestBody Room roomDetails) throws InvalidConfigurationPropertyValueException {
         Room room = repository.findById(roomId)
          .orElseThrow(() -> new InvalidConfigurationPropertyValueException("User not found on :: "+ roomId, roomDetails, roomId));

        room.setBookings(roomDetails.getBookings());
        final Room updatedRoom= repository.save(room);
        return ResponseEntity.ok(updatedRoom);
   }

	@GetMapping("/findAllRooms")
	public List<Room> getRooms(){
		return repository.findAll();
	}

	@GetMapping("/findAllRooms/{id}")
	public Optional<Room> getRoom(@PathVariable String id){
		return repository.findById(id);
	}

	@DeleteMapping("/deleteRoom/{id}")
	public String deleteRoom(@PathVariable String id){
		repository.deleteById(id);
		return "Room deleted with id : "+id;
	}
}
