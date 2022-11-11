package com.goldenraspberryawards.domain;

import com.goldenraspberryawards.domain.models.Filme;
import com.goldenraspberryawards.domain.models.IntervaloPremios;
import com.goldenraspberryawards.domain.models.IntervaloProdutor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProdutorService {

    private static final Integer QUANTIDADE_MINIMA_PREMIOS = 2;

    private final FilmeRepository filmeRepository;

    public IntervaloPremios getIntervaloPremios() {
        List<IntervaloProdutor> intervaloMinimo = new ArrayList<>();
        List<IntervaloProdutor> intervaloMaximo = new ArrayList<>();
        try {
            List<Filme> filmesVencedores = filmeRepository.findByWinner(true);
            Map<String, List<Filme>> filmesPorProdutor = filmesVencedores.stream().collect(Collectors.groupingBy(Filme::getProducer));

            List<IntervaloProdutor> intervalos = new ArrayList<>();

            filmesPorProdutor.forEach((produtor, filmes) -> {
                filmes.sort(Comparator.comparing(Filme::getYear));
                if (filmes.size() >= QUANTIDADE_MINIMA_PREMIOS) {
                    filmes.forEach(filme -> {
                        Filme proxFilme = getProxFilme(filmes, filme);
                        if (proxFilme != null) {
                            IntervaloProdutor intervalo = new IntervaloProdutor();
                            intervalo.setProducer(produtor);
                            intervalo.setPreviousWin(filme.getYear());
                            intervalo.setFollowingWin(proxFilme.getYear());
                            intervalo.setInterval(intervalo.getFollowingWin() - intervalo.getPreviousWin());
                            intervalos.add(intervalo);
                        }
                    });
                }
            });

            intervaloMinimo = intervalos.stream().filter(
                    intervaloProdutor -> intervaloProdutor.getInterval().equals(
                            intervalos.stream()
                                    .min(Comparator.comparing(IntervaloProdutor::getInterval))
                                    .orElseThrow(NoSuchElementException::new).getInterval()
                    )
            ).collect(Collectors.toList());

            intervaloMaximo = intervalos.stream().filter(
                    intervaloProdutor -> intervaloProdutor.getInterval().equals(
                            intervalos.stream()
                                    .max(Comparator.comparing(IntervaloProdutor::getInterval))
                                    .orElseThrow(NoSuchElementException::new).getInterval()
                    )
            ).collect(Collectors.toList());
        } catch (Exception e) {
            log.info("Ocorreu um erro ao trazer os dados: ", e);
        }
        return new IntervaloPremios(intervaloMinimo, intervaloMaximo);
    }

    private Filme getProxFilme(List<Filme> filmesVencedores, Filme filmeAtual) {
        int index = filmesVencedores.indexOf(filmeAtual);
        if (index < 0 || index + 1 == filmesVencedores.size()) {
            return null;
        }
        return filmesVencedores.get(index + 1);
    }

}
