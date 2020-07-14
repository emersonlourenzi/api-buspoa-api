package br.com.buspoa.contract.v1.line.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Document(value = "lines")
public class LineRequest {

    @Id
    @NotNull
    @NotEmpty(message = "Required id.")
    private String id;

    @NotNull
    @NotEmpty(message = "Required id bus.")
    private String idBus;

    @NotNull
    @NotEmpty(message = "Required code line.")
    private String codeLine;

    @NotNull
    @NotEmpty(message = "Required Name line.")
    private String nameLine;
}
