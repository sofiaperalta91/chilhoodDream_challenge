package org.example.childhooddream.controllers;

import org.example.childhooddream.entities.Train;
import org.example.childhooddream.repositories.TrainRepository;
import org.example.childhooddream.services.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
public class TrainController {

    @Autowired
    private TrainService trainService;
    @Autowired
    private TrainRepository trainRepository;

    @GetMapping("/trains")
    ResponseEntity<List<Train>> getTrains(@RequestParam(value = "keyword", required = false) String amenities) {
        List<Train> trainsList = trainService.getTrainsByAmenities(amenities);
        if (Objects.isNull(amenities)) {
            trainsList = trainService.getAllTrains();
        }
        return ResponseEntity.ok(trainsList);
    }

    @GetMapping("/trains/{id}")
    ResponseEntity<Train> getTrain(@PathVariable int id) {             //Capturar valor que faz parte do caminho.
        Optional<Train> train = trainService.getTrainById(id);
        if (train.isPresent()) {
            return ResponseEntity.ok(train.get());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Train not found.");
        }
    }

    @GetMapping("/trains/sharing-tracks")
    ResponseEntity<List<Train>> getTrainsSharingTrack() {
        List<Train> trains = trainService.getTrainsBySharingTracks(true);
        return ResponseEntity.ok(trains);
    }

    @RequestMapping("/**")
    public ResponseEntity<?> handleInvalidEndpoints() {
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body("{\"message\": \"Invalid endpoint.\"}");
    }

    @DeleteMapping("/trains/{id}")
    ResponseEntity<Object> deleteTrainId(@PathVariable int id) {
        boolean isDeleted = trainService.deleteTrainById(id);
        if (isDeleted) {
            return ResponseEntity.ok().body("{\"message\": \"Train removed successfully.\"}");
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Train not found.");
        }
    }

    @PutMapping("trains/{id}")
    ResponseEntity<Object> updateTrainId(@PathVariable int id, @RequestBody Train train) {
        Optional<Train> updatedTrain = trainService.getTrainById(id);
        if (updatedTrain.isPresent()) {
            trainService.updateTrain(updatedTrain.get(), train);
            return ResponseEntity.ok().body("{\"message\": \"Train edited successfully.\"}");
        } else {
            trainService.save(train);
            return ResponseEntity.ok().body("{\"message\": \"Train created successfully.\"}");
        }
    }
}

