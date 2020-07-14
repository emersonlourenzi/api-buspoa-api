package br.com.buspoa.contract.v1.line;

import br.com.buspoa.contract.v1.line.model.LineRequest;
import br.com.buspoa.contract.v1.line.model.LineResponse;
import br.com.buspoa.impl.line.repository.LineEntity;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.json.simple.parser.ParseException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("v1/lines")
@RestController
@AllArgsConstructor
public class LineController {
    private LineContractFacade lineContractFacade;

    @GetMapping("/list-lines-apibuspoa")
    @ApiOperation(value = "Retorna lista das linhas de ônibus direto da api buspoa.")
    public ResponseEntity<String> listLinesBusPoa() {
        return lineContractFacade.listLinesBusPoa();
    }


    @GetMapping("/name-line")
    @ApiOperation(value = "Busca no banco de dados por nome da linha e retorna a linha.")
    public List<LineEntity> searchByBusLineName(@RequestParam("name-line") String nameLine) {
        return lineContractFacade.searchByBusLineName(nameLine);
    }

    @GetMapping("/code-line")
    @ApiOperation(value = "Busca no banco de dados por código da linha e retorna a linha.")
    public List<LineEntity> searchByBusLineCode(@RequestParam("code-line") String codeLine) {
        return lineContractFacade.searchByBusLineCode(codeLine);
    }

    @GetMapping("/list-all-lines")
    @ApiOperation(value = "Retorna lista das linhas de ônibus cadastradas no banco de dados.")
    public List<LineEntity> listAllLines() {
        return lineContractFacade.listAllLines();
    }

    @PostMapping("/create-line-bus")
    @ApiOperation(value = "Cria linha e salva no banco de dados.")
    public LineResponse createLineBus(@RequestBody LineRequest lineBus) {
        return lineContractFacade.createLineBus(lineBus);
    }

    @PostMapping("/daily-update")
    @ApiOperation(value = "Método para atualização automática diária do banco de dados.")
    public void dailyUpdate() throws ParseException {
        lineContractFacade.dailyUpdate();
    }

    @DeleteMapping("/id/{id}")
    @ApiOperation(value = "Deleta do banco de dados a linha, buscando pelo id do banco.")
    public void deleteLineById(@Valid @PathVariable String id) {
        lineContractFacade.deleteLineById(id);
    }

    @DeleteMapping("/id-bus/{idBus}")
    @ApiOperation(value = "Deleta do banco de dados a linha, buscando pelo id da linha.")
    public void deleteLineByIdBus(@PathVariable String idBus) {
        lineContractFacade.deleteLineByIdBus(idBus);
    }

    @PutMapping("{idLine}")
    @ApiOperation(value = "Edita a linha no banco de dados, buscando pelo id da linha.")
    public LineResponse updateLine(@PathVariable String idLine, @RequestBody LineRequest lineRequest) {
        return lineContractFacade.updateLine(idLine, lineRequest);
    }

}

