package com.goldenraspberryawards.application;


import com.goldenraspberryawards.domain.ProdutorService;
import com.goldenraspberryawards.domain.models.IntervaloPremios;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/produtor")
@RequiredArgsConstructor
public class ProdutorController {

    private final ProdutorService produtorService;

    @GetMapping("/premios")
    public ResponseEntity<IntervaloPremios> buscarPremios() {
        IntervaloPremios intervaloPremios = produtorService.getIntervaloPremios();
        return new ResponseEntity<>(intervaloPremios, HttpStatus.OK);
    }

}