package com.goldenraspberryawards.domain.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class IntervaloProdutor implements Serializable {

    private String producer;
    private Long interval;
    private Long previousWin;
    private Long followingWin;

}
