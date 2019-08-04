package com.axway.qainterview;

import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/graph")
public class GraphController {

    private final AtomicLong counter = new AtomicLong();
    private final Map<Long, Graph> graphMap = new HashMap<>();
    @GetMapping
    public String getGraph(@RequestParam(value = "id") long id) {
        Graph graph = graphMap.get(id);
        if (graph!= null){
            return graph.printGraph();
        }

        return null;
    }

    @PostMapping
    public long createGraph(@RequestBody @NotNull GraphDTO requestGraph) {
        Graph graph= convertGraphDto(requestGraph);
        long graphID = counter.incrementAndGet();
        graphMap.put(graphID, graph);

        return graphID;
    }

    private Graph convertGraphDto(GraphDTO dto) {
        Graph grapifc = new Graph();
        if (dto.getVertices() != null) {
            for (String vertex : dto.getVertices()) {
                grapifc.addVertex(vertex);
            }
            for (Edge edge : dto.getEdges()) {
                grapifc.addEdge(edge.getLabel1(), edge.getLabel2());
            }
            return grapifc;
        }
        return null;
    }
}