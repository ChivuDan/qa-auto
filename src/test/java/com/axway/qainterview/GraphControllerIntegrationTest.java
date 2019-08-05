package com.axway.qainterview;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class GraphControllerIntegrationTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private GraphController controller;

    @Test
    public void contextLoads() throws Exception {
        assertThat(controller).isNotNull();
    }

        @Test
        public void createTest() throws Exception {
            GraphDTO graphDTO = new GraphDTO();
            List<String> vertices = new ArrayList<>();
            vertices.add("1");
            vertices.add("2");
            graphDTO.setVertices(vertices);

            List<Edge> edges = new ArrayList<>();
            edges.add(new Edge("1", "2"));
            graphDTO.setEdges(edges);

            assertThat(this.restTemplate.postForEntity("http://localhost:" + port + "/graph", graphDTO, String.class).getStatusCodeValue()).isEqualTo(200);
        }

    @Test
    public void readTest() throws Exception {
        GraphDTO graphDTO = new GraphDTO();
        List<String> vertices = new ArrayList<>();
        vertices.add("1");
        vertices.add("2");
        graphDTO.setVertices(vertices);

        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge("1", "2"));
        graphDTO.setEdges(edges);
        String graphID = this.restTemplate.postForEntity("http://localhost:" + port + "/graph", graphDTO, String.class).getBody();
        String readGraph = this.restTemplate.getForEntity("http://localhost:" + port + "/graph" + "?id=" + graphID, String.class).getBody();

        assertThat(readGraph).contains("2[1]");
        assertThat(readGraph).contains("1[2]");
    }

    @Test
    public void deleteTest() throws Exception {
        GraphDTO graphDTO = new GraphDTO();
        List<String> vertices = new ArrayList<>();
        vertices.add("1");
        vertices.add("2");
        graphDTO.setVertices(vertices);

        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge("1", "2"));
        graphDTO.setEdges(edges);
        String graphID = this.restTemplate.postForEntity("http://localhost:" + port + "/graph", graphDTO, String.class).getBody();

        this.restTemplate.delete("http://localhost:" + port + "/graph" + "?id=" + graphID, vertices);
        String readGraph = this.restTemplate.getForEntity("http://localhost:" + port + "/graph" + "?id=" + graphID, String.class).getBody();
       assertThat(readGraph).isEqualToIgnoringCase("Not found!");
    }

    @Test
    public void updateTest() throws Exception {
        //having
        GraphDTO graphDTO = new GraphDTO();
        List<String> vertices = new ArrayList<>();
        vertices.add("1");
        vertices.add("2");
        graphDTO.setVertices(vertices);

        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge("1", "2"));
        graphDTO.setEdges(edges);

        String graphID = this.restTemplate.postForEntity("http://localhost:" + port + "/graph", graphDTO, String.class).getBody();

        //when

        graphDTO.getEdges().add(new Edge("1","3"));
        graphDTO.getVertices().add("3");

        this.restTemplate.put("http://localhost:" + port + "/graph" + "?id=" + graphID, graphDTO);
        //then
        String result = this.restTemplate.getForEntity("http://localhost:" + port + "/graph" + "?id=" + graphID, String.class).getBody();

        assertThat(result).contains("3");
    }

}
