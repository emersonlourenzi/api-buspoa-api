package br.com.buspoa.contract.v1.line.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@AllArgsConstructor
@Data
@Builder
@Document(value = "lines")
public class LineResponse {
    private String id;
    private String idBus;
    private String codeLine;
    private String nameLine;
}
