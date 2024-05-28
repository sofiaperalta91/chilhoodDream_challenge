package org.example.childhooddream.services;

import org.example.childhooddream.entities.Train;
import org.example.childhooddream.repositories.TrainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrainService {
    @Autowired
    private TrainRepository trainRepository;

    public List<Train> getAllTrains() {
        return trainRepository.findAll();
    }

    public Optional<Train> getTrainById(int id) {
        return trainRepository.findById(id);
    }

    public List <Train> getTrainsBySharingTracks(boolean sharingTracks){
        return trainRepository.findBySharingTracks(sharingTracks);
    }
    public List <Train> getTrainsByAmenities(String keyword){
        return trainRepository.findByAmenitiesLike(keyword);
    }

    public boolean deleteTrainById(int id){
        if (trainRepository.findById(id).isPresent()){
            trainRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
