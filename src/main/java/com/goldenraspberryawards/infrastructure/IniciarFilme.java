package com.goldenraspberryawards.infrastructure;

import com.goldenraspberryawards.domain.models.Filme;
import com.goldenraspberryawards.infrastructure.models.LeitorFilmes;
import com.goldenraspberryawards.domain.FilmeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;

import java.io.File;

@Configuration
@RequiredArgsConstructor
public class IniciarFilme implements ApplicationRunner {

    private final FilmeRepository filmeRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        File arquivoCsv = ResourceUtils.getFile("classpath:movielist.csv");
        LeitorFilmes leitor = new FilmeArquivoCsvAdapter(arquivoCsv);
        leitor.execute().forEach(data -> {
            Filme filme = new Filme();
            filme.setYear(data.getAno());
            filme.setTitle(data.getTitulo());
            filme.setStudio(data.getEstudio());
            filme.setProducer(data.getProdutor());
            filme.setWinner(data.getVencedor());
            filmeRepository.save(filme);
        });
    }

}
