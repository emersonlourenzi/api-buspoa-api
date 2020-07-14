package br.com.buspoa.impl.line.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@Data
@Builder
@Document(value = "lines")
public class LineModel {
    @Id
    private String id;
    private String idBus;
    private String codeLine;
    private String nameLine;
}