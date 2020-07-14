package br.com.buspoa.impl.line;

import br.com.buspoa.impl.line.model.LineModel;
import br.com.buspoa.impl.line.repository.LineEntity;
import br.com.buspoa.impl.line.service.LineService;
import lombok.AllArgsConstructor;
import org.json.simple.parser.ParseException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Component
@AllArgsConstructor
public class LineFacade {
    private LineService lineService;

    public ResponseEntity<String> listLinesBusPoa() {
        return lineService.listLinesBusPoa();
    }

    public void dailyUpdate() throws ParseException {
        lineService.dailyUpdate();
    }

    public List<LineEntity> searchByBusLineName(String nameLine) {
        return lineService.searchByBusLineName(nameLine);
    }

    public List<LineEntity> searchByBusLineCode(@RequestParam("codeLine") String codeLine) {
        return lineService.searchByBusLineCode(codeLine);
    }

    public List<LineEntity> listAllLines() {
        return lineService.listAllLines();
    }

    public LineModel createLineBus(LineModel lineBus) {
        return lineService.createLineBus(lineBus);
    }

    public void deleteLineById(String id) {
        lineService.deleteLineById(id);
    }

    public void deleteLineByIdBus(String idBus) {
        lineService.deleteLineByIdBus(idBus);
    }

    public LineModel updateLine(String idLine, LineModel lineModel) {
        return lineService.updateLine(idLine, lineModel);
    }
}