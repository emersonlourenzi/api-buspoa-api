package br.com.buspoa.impl.itinerary.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@AllArgsConstructor
@Data
@Builder
@Document(value = "lines")
@EqualsAndHashCode
public class ItineraryModel {
    private String id;
    private String idLine;
    private String nameLine;
    private String codeLine;
    private ArrayList<Double> coordinates;
}
