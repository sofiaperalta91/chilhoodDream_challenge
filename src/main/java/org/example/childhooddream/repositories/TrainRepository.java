package org.example.childhooddream.repositories;

import org.example.childhooddream.entities.Train;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainRepository extends JpaRepository<Train, Integer> {

    List<Train> findBySharingTracks(boolean sharingTracks);


    List<Train> findByAmenitiesLike(@Param("keyword") String amenities);


}
