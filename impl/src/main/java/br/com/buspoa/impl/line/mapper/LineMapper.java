package br.com.buspoa.impl.line.mapper;

import br.com.buspoa.impl.line.model.LineModel;
import br.com.buspoa.impl.line.repository.LineEntity;

public class LineMapper {
    public static LineModel mapToModel(LineEntity lineEntity) {
        return LineModel.builder()
                .id(lineEntity.getId())
                .idBus(lineEntity.getIdBus())
                .codeLine(lineEntity.getCodeLine())
                .nameLine(lineEntity.getNameLine())
                .build();
    }

    public static LineEntity mapToEntity(LineModel lineModel) {
        return LineEntity.builder()
                .id(lineModel.getId())
                .idBus(lineModel.getIdBus())
                .codeLine(lineModel.getCodeLine())
                .nameLine(lineModel.getNameLine())
                .build();
    }
}
