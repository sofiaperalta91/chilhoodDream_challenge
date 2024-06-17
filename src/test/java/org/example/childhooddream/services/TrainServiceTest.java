package org.example.childhooddream.services;

import org.example.childhooddream.dto.TrainDTO;
import org.example.childhooddream.entities.Train;
import org.example.childhooddream.mappers.TrainMapper;
import org.example.childhooddream.repositories.TrainRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TrainServiceTest {
    @Mock
    private TrainRepository trainRepository;

    @Mock
    private TrainMapper trainMapper;

    @InjectMocks
    private TrainService trainService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllTrains() {

        //Arrange
        Train train1 = mock(Train.class);
        Train train2 = mock(Train.class);
        List<Train> trains = Arrays.asList(train1, train2);
        when(trainRepository.findAll()).thenReturn(trains);

        //Act
        List<Train> result = trainService.getAllTrains();

        //Assert
        assertEquals(trains.size(), result.size());

    }

    @Test
    void testGetTrainById_TruePresent() {
        // Arrange
        Train train = mock(Train.class);
        when(trainRepository.findById(1)).thenReturn(Optional.of(train));

        // Act
        Optional<Train> result = trainService.getTrainById(1);

        // Assert
        assertTrue(result.isPresent());
    }

    @Test
    void testGetTrainById_FalsePresent() {
        // Arrange
        when(trainRepository.findById(1)).thenReturn(Optional.empty());

        // Act
        Optional<Train> result = trainService.getTrainById(1);

        // Assert
        assertFalse(result.isPresent());
    }

    @Test
    void testGetTrainsBySharingTracks(){
        //Arrange
        Train train1 = mock(Train.class);
        Train train2 = mock(Train.class);
        List<Train> trains = Arrays.asList(train1, train2);
        when(trainRepository.findBySharingTracks(true)).thenReturn(trains);

        //Act
        List<Train> result = trainService.getTrainsBySharingTracks(true);

        //Assert
        assertEquals(trains.size(), result.size());
    }

    @Test
    void testGetTrainsByAmenities(){
        //Arrange
        Train train1 = mock(Train.class);
        Train train2 = mock(Train.class);
        List<Train> trains = Arrays.asList(train1, train2);
        when(trainRepository.findByAmenitiesLike("bar")).thenReturn(trains);

        //Act
        List<Train> result = trainService.getTrainsByAmenities("bar");

        //Assert
        assertEquals(trains.size(), result.size());
    }

    @Test
    void testDeleteTrainById_True(){
        //Arrange
        when(trainRepository.findById(1)).thenReturn(Optional.of(new Train()));

        //Act
        boolean result = trainService.deleteTrainById(1);

        //Assert
        assertTrue(result);

    }
    @Test
    void testDeleteTrainById_False(){
        //Arrange
        when(trainRepository.findById(1)).thenReturn(Optional.empty());

        //Act
        boolean result = trainService.deleteTrainById(1);

        //Assert
        assertFalse(result);

    }
    @Test
    void testUpdateTrain_WhenTrainExists(){

        //Arrange
            Train existingTrain = new Train();

            existingTrain.setId(1);
            existingTrain.setAmenities("");
            existingTrain.setDescription("");
            existingTrain.setDistanceBetweenStop("");
            existingTrain.setGradeCrossing(false);
            existingTrain.setMaxSpeed("");
            existingTrain.setName("");
            existingTrain.setSharingTracks(true);
            existingTrain.setTrainFrequency("");

            TrainDTO updatedTrainDetails = new TrainDTO();

            updatedTrainDetails.setAmenities("Premium");
            updatedTrainDetails.setDescription("New Description");
            updatedTrainDetails.setDistanceBetweenStop("150");
            updatedTrainDetails.setGradeCrossing(true);
            updatedTrainDetails.setMaxSpeed("100");
            updatedTrainDetails.setName("New Train");
            updatedTrainDetails.setSharingTracks(false);
            updatedTrainDetails.setTrainFrequency("15");

        //Act
        Train result = trainService.updateTrain(existingTrain, updatedTrainDetails);

        //Assert
        assertEquals("Premium", result.getAmenities());
        assertEquals("New Description", result.getDescription());
        assertEquals("150", result.getDistanceBetweenStop());
        assertEquals(true, result.getGradeCrossing());
        assertEquals("100", result.getMaxSpeed());
        assertEquals("New Train", result.getName());
        assertEquals(false, result.getSharingTracks());
        assertEquals("15", result.getTrainFrequency());

        }
    @Test
    void testSave(){

        //Arrange

        Train trainDetails = new Train();
        trainDetails.setId(1);
        trainDetails.setAmenities("Premium");
        trainDetails.setDescription("New Description");
        trainDetails.setDistanceBetweenStop("150");
        trainDetails.setGradeCrossing(true);
        trainDetails.setMaxSpeed("100");
        trainDetails.setName("New Train");
        trainDetails.setSharingTracks(false);
        trainDetails.setTrainFrequency("15");

        when(trainRepository.save(trainDetails)).thenReturn(trainDetails);

        //Act
        Train result = trainService.save(trainDetails);

        //Assert
        assertEquals("Premium", result.getAmenities());
        assertEquals("New Description", result.getDescription());
        assertEquals("150", result.getDistanceBetweenStop());
        assertEquals(true, result.getGradeCrossing());
        assertEquals("100", result.getMaxSpeed());
        assertEquals("New Train", result.getName());
        assertEquals(false, result.getSharingTracks());
        assertEquals("15", result.getTrainFrequency());

    }
    @Test
    void testCreateAndSaveTrain(){
        //Arrange
        int id = 1;
        String name = "New Train";
        String description = "New Description";
        String distanceBetweenStop = "150";
        String maxSpeed = "100";
        Boolean sharingTracks = false;
        Boolean gradeCrossing = true;
        String trainFrequency = "15";
        String amenities = "Premium";

        TrainDTO trainDTO = new TrainDTO(name, description, distanceBetweenStop, maxSpeed,sharingTracks,
                gradeCrossing, trainFrequency, amenities);
        Train train = new Train(id, name, description, distanceBetweenStop, maxSpeed, sharingTracks, gradeCrossing, trainFrequency, amenities);
        when(trainMapper.toEntity(trainDTO)).thenReturn(train);
        when(trainRepository.save(train)).thenReturn(train);

        //Act
        Train result = trainService.createAndSaveTrain(trainDTO);

        //Assert
        assertEquals(name, result.getName());
        assertEquals(description, result.getDescription());
        assertEquals(distanceBetweenStop, result.getDistanceBetweenStop());
        assertEquals(maxSpeed, result.getMaxSpeed());
        assertEquals(sharingTracks, result.getSharingTracks());
        assertEquals(gradeCrossing, result.getGradeCrossing());
        assertEquals(trainFrequency, result.getTrainFrequency());
        assertEquals(amenities, result.getAmenities());

    }
}
