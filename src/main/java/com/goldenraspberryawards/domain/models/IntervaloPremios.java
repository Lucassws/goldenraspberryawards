package com.goldenraspberryawards.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
public class IntervaloPremios implements Serializable {

    private List<IntervaloProdutor> min;
    private List<IntervaloProdutor> max;

}
