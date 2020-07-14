package br.com.buspoa.contract.v1.itinerary.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@AllArgsConstructor
@Data
@Builder
@Document(value = "itineraries")
public class ItineraryResponse {
    private String id;
    private String idLine;
    private String nameLine;
    private String codeLine;
    private ArrayList<Double> coordinates;
}
