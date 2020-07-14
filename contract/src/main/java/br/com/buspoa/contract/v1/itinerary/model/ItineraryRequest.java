package br.com.buspoa.contract.v1.itinerary.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Document(value = "itineraries")
public class ItineraryRequest {

    @Id
    @NotNull
    @NotEmpty(message = "Required code line.")
    private String id;

    @NotNull
    @NotEmpty(message = "Required id bus.")
    private String idLine;

    @NotNull
    @NotEmpty(message = "Required Name line.")
    private String nameLine;

    @NotNull
    @NotEmpty(message = "Required code line.")
    private String codeLine;

    private ArrayList<Double> coordinates;
}
