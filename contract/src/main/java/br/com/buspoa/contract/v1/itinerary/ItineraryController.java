package br.com.buspoa.contract.v1.itinerary;

import br.com.buspoa.contract.v1.itinerary.model.ItineraryRequest;
import br.com.buspoa.contract.v1.itinerary.model.ItineraryResponse;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("v1/itineraries")
@RestController
@AllArgsConstructor
public class ItineraryController {
    private ItineraryContractFacade itineraryContractFacade;

    @GetMapping("/apibuspoa")
    @ApiOperation(value = "Retorna lista de coordenadas das paradas direto da api, passando o id da linha.")
    public ResponseEntity<String> listItineraryByCodeLineBusPoa(@RequestParam("codeLine") String codeLine) {
        return itineraryContractFacade.listItineraryByCodeLineBusPoa(codeLine);
    }

    @GetMapping("/serch-by-radius")
    @ApiOperation(value = "Retorna lista de linhas em um determinado raio.")
    public List<String> serchByRadius(@RequestParam("longitude") double lon,
                                      @RequestParam("latitude") double lat,
                                      @RequestParam("radius") double rad) {
        return itineraryContractFacade.serchByRadius(lon, lat, rad);
    }

    @PostMapping("/create-itinerary")
    @ApiOperation(value = "Cria itineratio de ônibus.")
    public ItineraryResponse createItinerary(@RequestBody ItineraryRequest itineraryRequest) {
        return itineraryContractFacade.createItinerary(itineraryRequest);
    }

    @DeleteMapping("/id/{id}")
    @ApiOperation(value = "Deleta itinerario pelo id no banco.")
    public void deleteById(@PathVariable String id) {
        itineraryContractFacade.deleteById(id);
    }

    @DeleteMapping("/id-line/{idLine}")
    @ApiOperation(value = "Deleta itinerario pelo id da linha.")
    public void deletebyIdLine(@PathVariable String idLine) {
        itineraryContractFacade.deletebyIdLine(idLine);
    }

    @PutMapping("/{idLine}")
    @ApiOperation(value = "Edita itinerario.")
    public ItineraryResponse updateItinerary(@PathVariable String idLine,
                                             @RequestBody ItineraryRequest itineraryRequest) {
        return itineraryContractFacade.updateItinerary(idLine, itineraryRequest);
    }

}
