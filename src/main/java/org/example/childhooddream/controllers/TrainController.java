package org.example.childhooddream.controllers;

import org.example.childhooddream.entities.Train;
import org.example.childhooddream.services.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
public class TrainController {

    @Autowired
    private TrainService trainService;

    @GetMapping("/trains")
    ResponseEntity<List<Train>> getTrains() {
        return ResponseEntity.ok(trainService.getAllTrains());
    }
    @GetMapping("/trains/{id}")
    ResponseEntity <Train> getTrain(@PathVariable int id) {
        Optional<Train> train = trainService.getTrainById(id);
        if (train.isPresent()) {
            return ResponseEntity.ok(train.get());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Train not found.");
        }
    }

    @RequestMapping("/**")
    public ResponseEntity<?> handleInvalidEndpoints() {
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body("{\"message\": \"invalid endpoint\"}");
    }
}
