package br.com.buspoa.contract.v1.line;

import br.com.buspoa.contract.v1.line.mapper.LineMapper;
import br.com.buspoa.contract.v1.line.model.LineRequest;
import br.com.buspoa.contract.v1.line.model.LineResponse;
import br.com.buspoa.impl.line.LineFacade;
import br.com.buspoa.impl.line.repository.LineEntity;
import lombok.AllArgsConstructor;
import org.json.simple.parser.ParseException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Component
@AllArgsConstructor
public class LineContractFacade {
    private LineFacade lineFacade;

    ResponseEntity<String> listLinesBusPoa() {
        return lineFacade.listLinesBusPoa();
    }

    public void dailyUpdate() throws ParseException {
        lineFacade.dailyUpdate();
    }

    List<LineEntity> searchByBusLineName(String nameLine) {
        return lineFacade.searchByBusLineName(nameLine);
    }

    List<LineEntity> searchByBusLineCode(@RequestParam("codeLine") String codeLine) {
        return lineFacade.searchByBusLineCode(codeLine);
    }

    List<LineEntity> listAllLines() {
        return lineFacade.listAllLines();
    }

    LineResponse createLineBus(LineRequest lineBus) {
        return LineMapper.mapToContract(lineFacade.createLineBus(LineMapper.mapToImpl(lineBus)));
    }

    void deleteLineById(String id) {
        lineFacade.deleteLineById(id);
    }

    void deleteLineByIdBus(String idBus) {
        lineFacade.deleteLineByIdBus(idBus);
    }

    public LineResponse updateLine(String idLine, LineRequest lineRequest) {
        return LineMapper.mapToContract(lineFacade.updateLine(idLine, LineMapper.mapToImpl(lineRequest)));
    }
}