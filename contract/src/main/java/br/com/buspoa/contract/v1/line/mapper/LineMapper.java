package br.com.buspoa.contract.v1.line.mapper;

import br.com.buspoa.contract.v1.line.model.LineRequest;
import br.com.buspoa.contract.v1.line.model.LineResponse;
import br.com.buspoa.impl.line.model.LineModel;

public class LineMapper {
    public static LineResponse mapToContract(LineModel lineModel) {
        return LineResponse.builder()
                .id(lineModel.getId())
                .idBus(lineModel.getIdBus())
                .codeLine(lineModel.getCodeLine())
                .nameLine(lineModel.getNameLine())
                .build();
    }

    public static LineModel mapToImpl(LineRequest lineRequest) {
        return LineModel.builder()
                .id(lineRequest.getId())
                .idBus(lineRequest.getIdBus())
                .codeLine(lineRequest.getCodeLine())
                .nameLine(lineRequest.getNameLine())
                .build();
    }
}
