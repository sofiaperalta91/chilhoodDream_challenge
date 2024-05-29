package org.example.childhooddream.services;

import jakarta.transaction.Transactional;
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

    @Transactional
    public Train updateTrain(Train train, Train trainDetails) {
                    train.setAmenities(trainDetails.getAmenities());
                    train.setDescription(trainDetails.getDescription());
                    train.setDistanceBetweenStop(trainDetails.getDistanceBetweenStop());
                    train.setGradeCrossing(trainDetails.getGradeCrossing());
                    train.setMaxSpeed(trainDetails.getMaxSpeed());
                    train.setName(trainDetails.getName());
                    train.setSharingTracks(trainDetails.getSharingTracks());
                    train.setTrainFrequency(trainDetails.getTrainFrequency());
    return train;
    }

    public Train save(Train train){
        return trainRepository.save(train);
    }
}
