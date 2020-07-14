package br.com.buspoa.impl.line.repository;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Document(value = "lines")
@EqualsAndHashCode
public class LineEntity {

    @Id
    private String id;

    @NotNull
    @NotEmpty(message = "Required idBus field.")
    private String idBus;

    @NotNull
    @NotEmpty(message = "Required codeLine field.")
    private String codeLine;

    @NotNull
    @NotEmpty(message = "Required nameLine field.")
    private String nameLine;

}
