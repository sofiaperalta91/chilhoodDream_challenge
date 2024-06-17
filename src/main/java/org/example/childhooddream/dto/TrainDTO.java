package org.example.childhooddream.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TrainDTO {
    @NotBlank(message = "Name is mandatory")
    private String name;
    @NotBlank(message = "Description is mandatory")
    private String description;
    @NotBlank(message = "Distance between stop is mandatory")
    private String distanceBetweenStop;
    @NotBlank(message = "Max speed is mandatory")
    private String maxSpeed;
    private Boolean sharingTracks;
    private Boolean gradeCrossing;
    @NotBlank(message = "Frequency is mandatory")
    private String trainFrequency;
    @NotBlank(message = "Amenities is mandatory")
    private String amenities;
}
