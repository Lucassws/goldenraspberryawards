package com.goldenraspberryawards;

import com.goldenraspberryawards.domain.models.IntervaloPremios;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GoldenRaspberryAwardsApplicationTests {

    private static final String HOST = "http://localhost:";
    private static final String PRODUCERS_RESOURCE = "/goldenraspberryawards-api/v1/produtor";

    @Value("${local.server.port}")
    int port;

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    public void testeDePremiosCenarioSucesso() {
        ResponseEntity<IntervaloPremios> responseEntity = restTemplate.getForEntity(HOST + port + PRODUCERS_RESOURCE + "/premios", IntervaloPremios.class);
        Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        Assert.assertNotNull(responseEntity.getBody());
        Assert.assertNotNull(responseEntity.getBody().getMax());
        Assert.assertNotNull(responseEntity.getBody().getMin());
    }

}