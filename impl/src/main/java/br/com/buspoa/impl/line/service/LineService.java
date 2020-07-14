package br.com.buspoa.impl.line.service;

import br.com.buspoa.impl.error.NoContent;
import br.com.buspoa.impl.error.NotFound;
import br.com.buspoa.impl.itinerary.ItineraryFacade;
import br.com.buspoa.impl.itinerary.service.ItineraryService;
import br.com.buspoa.impl.line.mapper.LineMapper;
import br.com.buspoa.impl.line.model.LineModel;
import br.com.buspoa.impl.line.repository.LineEntity;
import br.com.buspoa.impl.line.repository.LineRepository;
import com.mongodb.MongoClient;
import lombok.AllArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@AllArgsConstructor
public class LineService {
    private LineRepository repository;
    ItineraryFacade itineraryFacade;
    ItineraryService itineraryService;
    private final MongoClient mongoClient = new MongoClient();
    private final MongoTemplate mongoTemplate = new MongoTemplate(mongoClient, "buspoadb");

    public ResponseEntity<String> listLinesBusPoa() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://www.poatransporte.com.br/php/facades/process.php?a=nc&p=%&t=o";
        return restTemplate.getForEntity(url, String.class);
    }

    public void dailyUpdate() throws ParseException {
        JSONArray jsonArray;

        JSONObject jsonObject;
        Object object;

        ResponseEntity<String> lines = listLinesBusPoa();

        JSONParser parser = new JSONParser();

        object = parser.parse(String.valueOf(lines.getBody()));
        jsonArray = (JSONArray) object;

        for (Object line : jsonArray) {
            jsonObject = (JSONObject) line;
            LineEntity lineEntity = new LineEntity();

            lineEntity.setIdBus(jsonObject.get("id").toString());
            lineEntity.setCodeLine(jsonObject.get("codigo").toString());
            lineEntity.setNameLine(jsonObject.get("nome").toString());

            final Query query = new Query();
            query.addCriteria(Criteria.where("idBus").is(lineEntity.getIdBus()));
            LineEntity lineEntity1 = mongoTemplate.findOne(query, LineEntity.class);

            assert lineEntity1 != null;
            if (!repository.existsById(lineEntity1.getId())) {
                repository.save(lineEntity);
            }

            itineraryService.dailyUpdateItinerary(lineEntity.getIdBus());
        }
    }

//    public void saveDataDB() throws ParseException {
//        JSONArray jsonArray;
//        JSONObject jsonObject;
//        Object object;
//
//        ResponseEntity<String> lines = listLinesBusPoa();
//
//        JSONParser parser = new JSONParser();
//
//        object = parser.parse(String.valueOf(lines.getBody()));
//        jsonArray = (JSONArray) object;
//
//        for (Object line : jsonArray) {
//            jsonObject = (JSONObject) line;
//            LineEntity lineEntity = new LineEntity();
//
//            lineEntity.setIdBus(jsonObject.get("id").toString());
//            lineEntity.setCodeLine(jsonObject.get("codigo").toString());
//            lineEntity.setNameLine(jsonObject.get("nome").toString());
//
//            repository.save(lineEntity);
//
//        }
//    }

    public List<LineEntity> searchByBusLineName(String nameLine) {
        final Query query = new Query();
        query.addCriteria(Criteria.where("nameLine").is(nameLine));
        LineEntity lineEntity = mongoTemplate.findOne(query, LineEntity.class);
        if (lineEntity != null && repository.existsById(lineEntity.getId())) {
            return mongoTemplate.find(query, LineEntity.class);
        }
        else {
            throw new NotFound("nameLine not found");
        }
    }

    public List<LineEntity> searchByBusLineCode(String codeLine) {
        final Query query = new Query();
        query.addCriteria(Criteria.where("codeLine").is(codeLine));
        LineEntity lineEntity = mongoTemplate.findOne(query, LineEntity.class);
        if (lineEntity != null && repository.existsById(lineEntity.getId())) {
            return mongoTemplate.find(query, LineEntity.class);
        }
        else {
            throw new NotFound("codeLine not found");
        }
    }

    public List<LineEntity> listAllLines() {
        return repository.findAll();
    }

    public LineModel createLineBus(LineModel lineBus) {
        if (lineBus.getIdBus() == null || lineBus.getIdBus().equals("") ||
                lineBus.getCodeLine() == null || lineBus.getCodeLine().equals("") ||
                lineBus.getNameLine() == null || lineBus.getNameLine().equals("")) {
            throw new NotFound("Invalid or unfilled data");
        }
        else {

            final Query query1 = new Query();
            final Query query2 = new Query();
            final Query query3 = new Query();

            final MongoTemplate mongoTemplate1 = new MongoTemplate(mongoClient, "buspoadb");
            final MongoTemplate mongoTemplate2 = new MongoTemplate(mongoClient, "buspoadb");
            final MongoTemplate mongoTemplate3 = new MongoTemplate(mongoClient, "buspoadb");

            query1.addCriteria(Criteria.where("idBus").is(lineBus.getIdBus()));
            query2.addCriteria(Criteria.where("codeLine").is(lineBus.getCodeLine()));
            query3.addCriteria(Criteria.where("nameLine").is(lineBus.getNameLine()));

            LineEntity lineEntity1  = mongoTemplate1.findOne(query1, LineEntity.class);
            LineEntity lineEntity2  = mongoTemplate2.findOne(query2, LineEntity.class);
            LineEntity lineEntity3  = mongoTemplate3.findOne(query3, LineEntity.class);

            if (lineEntity1 != null && repository.existsById(lineEntity1.getId())) {
                throw new NotFound("idBus already exists.");
            } else if (lineEntity2 != null && repository.existsById(lineEntity2.getId())) {
                throw new NotFound("codeLine already exists.");
            } else if (lineEntity3 != null && repository.existsById(lineEntity3.getId())) {
                throw new NotFound("nameLine already exists.");
            } else {
                return LineMapper.mapToModel(repository.save(LineMapper.mapToEntity(lineBus)));
            }
        }
    }

    public void deleteLineById(String id) {
        final Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));
        LineEntity lineEntity = mongoTemplate.findOne(query, LineEntity.class);
        if (lineEntity != null) {
            mongoTemplate.remove(query, "lines");
            itineraryFacade.deleteById(id);
            throw new NoContent("Line successfully deleted");
        } else {
            throw new NotFound("nonexistent id");
        }
    }

    public void deleteLineByIdBus(String idLine) {
        final Query query = new Query();
        query.addCriteria(Criteria.where("idBus").is(idLine));
        LineEntity lineEntity = mongoTemplate.findOne(query, LineEntity.class);
        if (lineEntity != null) {
            mongoTemplate.remove(query, "lines");
            itineraryFacade.deletebyIdLine(idLine);
            throw new NoContent("Line successfully deleted");
        } else {
            throw new NotFound("Nonexistent id line");
        }
    }

    public LineModel updateLine(String idLine, LineModel lineModel) {
        final Query query = new Query();
        query.addCriteria(Criteria.where("idBus").is(idLine));
        LineEntity lineEntity = mongoTemplate.findOne(query, LineEntity.class);
        if (lineEntity != null && repository.existsById(lineEntity.getId())) {
            repository.deleteById(lineEntity.getId());
            lineModel.setId(lineEntity.getId());
            return LineMapper.mapToModel(repository.save(LineMapper.mapToEntity(lineModel)));
        } else {
            throw new NotFound("Nonexistent id line");
        }
    }
}
