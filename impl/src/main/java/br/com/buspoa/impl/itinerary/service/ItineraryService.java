package br.com.buspoa.impl.itinerary.service;

import br.com.buspoa.impl.error.NoContent;
import br.com.buspoa.impl.error.NotFound;
import br.com.buspoa.impl.itinerary.mapper.ItineraryMapper;
import br.com.buspoa.impl.itinerary.model.ItineraryModel;
import br.com.buspoa.impl.itinerary.repository.ItineraryEntity;
import br.com.buspoa.impl.itinerary.repository.ItineraryRepository;
import com.mongodb.MongoClient;
import lombok.AllArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.data.geo.*;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.NearQuery;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ItineraryService {
    private ItineraryRepository repository;
    private final MongoClient mongoClient = new MongoClient();
    private final MongoTemplate mongoTemplate = new MongoTemplate(mongoClient, "buspoadb");

    public void dailyUpdateItinerary(String idLine) throws ParseException {
        ResponseEntity<String> itineraries = listItineraryByCodeLineBusPoa(idLine);
        String itinerary = itineraries.getBody().replaceAll("(\")(\\d+)(\":)", "");
        itinerary = itinerary.replace("}}", "}]");
        itinerary = itinerary.trim().replaceAll("(\"\\w+\")(:\"\\w+\",\"\\w+\":\")(.+)(\"codigo\":\")([A-Z0-9]+\\-[A-Z0-9]+\",)", "[");
        itinerary = itinerary.replace("{[{", "[{");
        // System.out.println(itinerary);
        JSONArray jsonArray;

        JSONObject jsonObject;
        Object object;

        JSONParser parser = new JSONParser();

        object = parser.parse(itinerary);
        jsonArray = (JSONArray) object;

        for (Object itin : jsonArray) {
            jsonObject = (JSONObject) itin;

            ItineraryEntity itineraryEntity = new ItineraryEntity();
            ArrayList<Double> coord = new ArrayList<>();
            coord.add(Double.parseDouble((String) jsonObject.get("lng")));
            coord.add(Double.parseDouble((String) jsonObject.get("lat")));
            itineraryEntity.setCoordinates(coord);
            itineraryEntity.setCodeLine(idLine);

            final Query query = new Query();
            query.addCriteria(Criteria.where("codeLine").is(itineraryEntity.getIdLine()));
            ItineraryEntity itineraryEntity1 = mongoTemplate.findOne(query, ItineraryEntity.class);

            assert itineraryEntity1 != null;
            if (!repository.existsById(itineraryEntity1.getId())) {
                repository.save(itineraryEntity);
            }
//            repository.save(itineraryEntity);
            System.out.println(coord);
        }
    }

    public List<String> serchByRadius(double lon, double lat, double rad) {
        Point location = new Point(lon, lat);

        List<String> lines = new ArrayList<>();
        NearQuery query = NearQuery.near(location).maxDistance(new Distance(rad, Metrics.KILOMETERS));
        GeoResults<ItineraryEntity> a = mongoTemplate.geoNear(query, ItineraryEntity.class);
        for (GeoResult<ItineraryEntity> geo : a.getContent()) {
            ItineraryEntity itin = geo.getContent();
            if (!lines.contains(itin.getCodeLine())) {
                lines.add(itin.getCodeLine());
            }
        }
        System.out.println(lines);
        return lines;
    }

    public ResponseEntity<String> listItineraryByCodeLineBusPoa(String codeLine) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://www.poatransporte.com.br/php/facades/process.php?a=il&p=";
        return restTemplate.getForEntity(url + codeLine, String.class);
    }

    public ItineraryModel createItinerary(ItineraryModel itineraryModel) {
        return ItineraryMapper.mapToModel(repository.save(ItineraryMapper
                .mapToEntity(itineraryModel)));
    }

    public void deleteById(String id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            throw new NoContent("Itinerary successfully deleted");
        } else {
            throw new NotFound("nonexistent id");
        }
//        final Query query = new Query();
//        query.addCriteria(Criteria.where("_id").is(id));
//        LineEntity lineEntity = mongoTemplate.findOne(query, LineEntity.class);
//        if (lineEntity != null) {
//            mongoTemplate.remove(query, "itineraries");
//            throw new NoContent("Itinerary successfully deleted");
//        } else {
//            throw new NotFound("nonexistent id");
//        }
    }

    public void deletebyIdLine(String idLine) {
        final Query query = new Query();
        query.addCriteria(Criteria.where("idBus").is(idLine));
        ItineraryEntity entity = mongoTemplate.findOne(query, ItineraryEntity.class);
        if (entity != null && repository.existsById(entity.getId())) {
            repository.deleteById(entity.getId());
//            mongoTemplate.remove(query, "itineraries");
            throw new NoContent("Itinerary successfully deleted");
        } else {
            throw new NotFound("Nonexistent id itinerary");
        }
    }

    public ItineraryModel update(String idLine, ItineraryModel itineraryModel) {
        final Query query = new Query();
        query.addCriteria(Criteria.where("idLine").is(idLine));
        ItineraryEntity entity = mongoTemplate.findOne(query, ItineraryEntity.class);
        if (entity != null && repository.existsById(entity.getId())) {
            repository.deleteById(entity.getId());
            itineraryModel.setId(entity.getId());
            return ItineraryMapper.mapToModel(repository
                    .save(ItineraryMapper.mapToEntity(itineraryModel)));
        } else {
            throw new NotFound("Nonexistent idLine itinerary");
        }
    }
}
