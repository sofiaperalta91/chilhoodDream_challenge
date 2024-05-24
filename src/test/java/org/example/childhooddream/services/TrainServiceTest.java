package org.example.childhooddream.services;

import org.example.childhooddream.entities.Train;
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
}