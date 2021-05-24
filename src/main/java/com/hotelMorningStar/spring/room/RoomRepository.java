package com.hotelMorningStar.spring.room;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.hotelMorningStar.spring.room.Room;

@Repository
public interface RoomRepository extends MongoRepository<Room, String> {

}

