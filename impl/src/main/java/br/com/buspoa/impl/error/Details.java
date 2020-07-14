package br.com.buspoa.impl.error;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Details {
    private String title;
    private int status;
    private String detail;
    private Long timestamp;
    private String developerMessage;
}
