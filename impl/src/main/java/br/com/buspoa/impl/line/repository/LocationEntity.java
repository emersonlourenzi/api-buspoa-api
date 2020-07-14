package br.com.buspoa.impl.line.repository;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Document(value = "lines")
@EqualsAndHashCode
public class LocationEntity {
    private List<Double> coordinates;
}
