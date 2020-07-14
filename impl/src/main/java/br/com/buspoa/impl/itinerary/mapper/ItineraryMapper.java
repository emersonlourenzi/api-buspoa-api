package br.com.buspoa.impl.itinerary.mapper;

import br.com.buspoa.impl.itinerary.model.ItineraryModel;
import br.com.buspoa.impl.itinerary.repository.ItineraryEntity;

public class ItineraryMapper {
    public static ItineraryModel mapToModel(ItineraryEntity itineraryEntity) {
        return ItineraryModel.builder()
                .id(itineraryEntity.getId())
                .idLine(itineraryEntity.getIdLine())
                .codeLine(itineraryEntity.getCodeLine())
                .nameLine(itineraryEntity.getNameLine())
                .coordinates(itineraryEntity.getCoordinates())
                .build();
    }

    public static ItineraryEntity mapToEntity(ItineraryModel itineraryModel) {
        return ItineraryEntity.builder()
                .id(itineraryModel.getId())
                .idLine(itineraryModel.getIdLine())
                .codeLine(itineraryModel.getCodeLine())
                .nameLine(itineraryModel.getNameLine())
                .coordinates(itineraryModel.getCoordinates())
                .build();
    }
}
