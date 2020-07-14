package br.com.buspoa.impl.itinerary.repository;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Document(value = "itineraries")
@EqualsAndHashCode
public class ItineraryEntity {
    private String id;
    private String idLine;
    private String nameLine;
    private String codeLine;
    private ArrayList<Double> coordinates;
}
