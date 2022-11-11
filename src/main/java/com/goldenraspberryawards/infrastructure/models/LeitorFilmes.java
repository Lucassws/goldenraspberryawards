package com.goldenraspberryawards.infrastructure.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

public interface LeitorFilmes {

    @Data
    @NoArgsConstructor
    class PropriedadesFilme {
        private Long ano;
        private String titulo;
        private String estudio;
        private String produtor;
        private Boolean vencedor;
    }

    List<PropriedadesFilme> execute();

}
