package br.com.buspoa.contract.v1.itinerary;

import br.com.buspoa.contract.v1.itinerary.mapper.ItineraryMapperContract;
import br.com.buspoa.contract.v1.itinerary.model.ItineraryRequest;
import br.com.buspoa.contract.v1.itinerary.model.ItineraryResponse;
import br.com.buspoa.impl.itinerary.ItineraryFacade;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class ItineraryContractFacade {
    private ItineraryFacade itineraryFacade;

    ResponseEntity<String> listItineraryByCodeLineBusPoa(String codeLine) {
        return itineraryFacade.listItineraryByCodeLineBusPoa(codeLine);
    }

    public ItineraryResponse createItinerary(ItineraryRequest itineraryRequest) {
        return ItineraryMapperContract.mapToContract(itineraryFacade
                .createItinerary(ItineraryMapperContract.mapToImpl(itineraryRequest)));
    }

    public void deleteById(String id) {
        itineraryFacade.deleteById(id);
    }

    public void deletebyIdLine(String idLine) {
        itineraryFacade.deletebyIdLine(idLine);
    }

    public ItineraryResponse updateItinerary(String idLine, ItineraryRequest itineraryRequest) {
        return ItineraryMapperContract.mapToContract(itineraryFacade.
                update(idLine, ItineraryMapperContract.mapToImpl(itineraryRequest)));
    }

    public List<String> serchByRadius(double lon, double lat, double rad) {
        return itineraryFacade.serchByRadius(lon, lat, rad);
    }
}
