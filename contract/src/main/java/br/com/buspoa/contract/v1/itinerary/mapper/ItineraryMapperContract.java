package br.com.buspoa.contract.v1.itinerary.mapper;

import br.com.buspoa.contract.v1.itinerary.model.ItineraryRequest;
import br.com.buspoa.contract.v1.itinerary.model.ItineraryResponse;
import br.com.buspoa.impl.itinerary.model.ItineraryModel;

public class ItineraryMapperContract {
    public static ItineraryResponse mapToContract(ItineraryModel itineraryModel) {
        return ItineraryResponse.builder()
                .id(itineraryModel.getId())
                .idLine(itineraryModel.getIdLine())
                .codeLine(itineraryModel.getCodeLine())
                .nameLine(itineraryModel.getNameLine())
                .coordinates(itineraryModel.getCoordinates())
                .build();
    }

    public static ItineraryModel mapToImpl(ItineraryRequest itineraryRequest) {
        return ItineraryModel.builder()
                .id(itineraryRequest.getId())
                .idLine(itineraryRequest.getIdLine())
                .codeLine(itineraryRequest.getCodeLine())
                .nameLine(itineraryRequest.getNameLine())
                .coordinates(itineraryRequest.getCoordinates())
                .build();
    }
}
