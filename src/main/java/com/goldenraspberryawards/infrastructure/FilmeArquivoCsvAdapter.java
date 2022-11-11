package com.goldenraspberryawards.infrastructure;

import com.goldenraspberryawards.infrastructure.models.LeitorFilmes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
public class FilmeArquivoCsvAdapter implements LeitorFilmes {

    private static final String DELIMITADOR_DE_VIRGULA = ";";
    private static final String PULA_TEXTO = "year";
    private static final Integer ANO_INDEX = 0;
    private static final Integer TITULO_INDEX = 1;
    private static final Integer ESTUDIO_INDEX = 2;
    private static final Integer PRODUTOR_INDEX = 3;
    private static final Integer VENCEDOR_INDEX = 4;

    private final File csvFile;

    @Override
    public List<PropriedadesFilme> execute() {
        List<PropriedadesFilme> filmes = new ArrayList<>();
        buscarRegistros().forEach(values -> {
            String[] produtores = values[PRODUTOR_INDEX].split("(and|,)");
            for (String produtor : produtores) {
                if (!Strings.isBlank(produtor)) {
                    PropriedadesFilme filme = new PropriedadesFilme();
                    filme.setAno(Long.valueOf(values[ANO_INDEX]));
                    filme.setTitulo(values[TITULO_INDEX].trim());
                    filme.setEstudio(values[ESTUDIO_INDEX].trim());
                    filme.setVencedor(false);
                    if (values.length > 4 && values[VENCEDOR_INDEX] != null) {
                        filme.setVencedor(values[VENCEDOR_INDEX].equalsIgnoreCase("yes"));
                    }
                    filme.setProdutor(produtor.trim());
                    filmes.add(filme);
                }
            }
        });
        return filmes;
    }

    private List<String[]> buscarRegistros() {
        List<String[]> registros = new ArrayList<>();
        try (BufferedReader csvReader = new BufferedReader(new FileReader(csvFile))) {
            String line;
            while ((line = csvReader.readLine()) != null) {
                String[] values = line.split(DELIMITADOR_DE_VIRGULA);
                if (!values[ANO_INDEX].equalsIgnoreCase(PULA_TEXTO)) {
                    registros.add(values);
                }
            }
        } catch (Exception e) {
            log.info("Houve um erro ao buscar resgistros no CSV: ", e);
        }
        return registros;
    }

}
