package com.digital.crud.saladereuniao.saladereuniao.controller;


import com.digital.crud.saladereuniao.saladereuniao.model.Room;
import com.digital.crud.saladereuniao.saladereuniao.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/")
@CrossOrigin
public class RoomController {

    @Autowired
    private RoomRepository roomRepository;

    // 1. Buscar todas as salas
    @GetMapping
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    // 2. Buscar uma sala por ID
    @GetMapping("/{id}")
    public ResponseEntity<Room> getRoomById(@PathVariable Long id) {
        Optional<Room> room = roomRepository.findById(id);
        if (room.isPresent()) {
            return ResponseEntity.ok(room.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    // 3. Criar uma nova sala
    @PostMapping
    public Room createRoom(@RequestBody Room room) {
        return roomRepository.save(room);
    }

    // 4. Atualizar uma sala existente
    @PutMapping("/{id}")
    public ResponseEntity<Room> updateRoom(@PathVariable Long id, @RequestBody Room roomDetails) {
        Optional<Room> optionalRoom = roomRepository.findById(id);
        if (optionalRoom.isPresent()) {
            Room room = optionalRoom.get();
            room.setName(roomDetails.getName());
            room.setDate(roomDetails.getDate());
            room.setStartHour(roomDetails.getStartHour());
            room.setEndHour(roomDetails.getEndHour());
            return ResponseEntity.ok(roomRepository.save(room));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 5. Deletar uma sala
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoom(@PathVariable Long id) {
        if (roomRepository.existsById(id)) {
            roomRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
 
}
