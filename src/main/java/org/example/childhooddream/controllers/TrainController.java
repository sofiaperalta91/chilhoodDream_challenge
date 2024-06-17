package org.example.childhooddream.controllers;

import jakarta.validation.Valid;
import org.example.childhooddream.dto.TrainDTO;
import org.example.childhooddream.entities.Train;
import org.example.childhooddream.repositories.TrainRepository;
import org.example.childhooddream.services.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
public class TrainController {

    @Autowired
    private TrainService trainService;

    @GetMapping("/trains")
    ResponseEntity<List<Train>> getTrains(@RequestParam(value = "keyword", required = false) String amenities) {
        List<Train> trainsList = trainService.getTrainsByAmenities(amenities);
        if (Objects.isNull(amenities)) {
            trainsList = trainService.getAllTrains();
        }
        return ResponseEntity.ok(trainsList);
    }

    @GetMapping("/trains/{id}")
    ResponseEntity<Train> getTrain(@PathVariable int id) {             //Capturar o valor que faz parte do caminho.
        Optional<Train> train = trainService.getTrainById(id);
        if (train.isPresent()) {
            return ResponseEntity.ok(train.get());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/trains/sharing-tracks")
    ResponseEntity<List<Train>> getTrainsSharingTrack() {
        List<Train> trains = trainService.getTrainsBySharingTracks(true);
        return ResponseEntity.ok(trains);
    }

    @RequestMapping("/**")
    public ResponseEntity<?> handleInvalidEndpoints() {
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).build();
    }

    @DeleteMapping("/trains/{id}")
    ResponseEntity<Object> deleteTrainId(@PathVariable int id) {
        boolean isDeleted = trainService.deleteTrainById(id);
        if (isDeleted) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/trains/{id}")
    ResponseEntity<Object> updateTrainId(@PathVariable int id, @RequestBody TrainDTO trainDTO) throws URISyntaxException {
        Optional<Train> updatedTrain = trainService.getTrainById(id);
        if (updatedTrain.isPresent()) {
            trainService.updateTrain(updatedTrain.get(), trainDTO);
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            Train createdTrain = trainService.createAndSaveTrain(trainDTO);
            return ResponseEntity.created(new URI("/api/trains/" + createdTrain.getId())).build();
        }
    }

    @PostMapping("/trains")
        ResponseEntity<Object> createTrain(@RequestBody @Valid TrainDTO trainDTO) throws URISyntaxException {
        Train newTrain = trainService.createAndSaveTrain(trainDTO);
        return ResponseEntity.created(new URI("/api/trains/" + newTrain.getId())).build();
    }
}

