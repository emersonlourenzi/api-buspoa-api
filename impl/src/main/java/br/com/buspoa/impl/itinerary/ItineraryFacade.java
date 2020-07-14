package br.com.buspoa.impl.itinerary;

import br.com.buspoa.impl.itinerary.model.ItineraryModel;
import br.com.buspoa.impl.itinerary.service.ItineraryService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class ItineraryFacade {
    private ItineraryService itineraryService;

    public ResponseEntity<String> listItineraryByCodeLineBusPoa(String codeLine) {
        return itineraryService.listItineraryByCodeLineBusPoa(codeLine);
    }

    public ItineraryModel createItinerary(ItineraryModel itineraryModel) {
        return itineraryService.createItinerary(itineraryModel);
    }

    public void deleteById(String id) {
        itineraryService.deleteById(id);
    }

    public void deletebyIdLine(String idLine) {
        itineraryService.deletebyIdLine(idLine);
    }

    public ItineraryModel update(String idLine, ItineraryModel itineraryModel) {
        return itineraryService.update(idLine, itineraryModel);
    }

    public List<String> serchByRadius(double lon, double lat, double rad) {
        return itineraryService.serchByRadius(lon, lat, rad);
    }
}