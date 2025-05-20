package org.insa.graphs.gui.simple;

import org.insa.graphs.algorithm.ArcInspector;
import org.insa.graphs.algorithm.shortestpath.AStarAlgorithm;
import org.insa.graphs.algorithm.shortestpath.ShortestPathData;
import org.insa.graphs.algorithm.shortestpath.ShortestPathSolution;
import org.insa.graphs.model.Graph;

public class TestAstar extends testAlgoChemin {

    public void dotest(String map, int start, int end, ArcInspector arcInsp){
        System.out.println("TEST A*");

        System.out.println(testRoute(map, start, end,arcInsp));
    }

    public void dotestAlea(String map, ArcInspector arcInsp){
        System.out.println("TEST A*");
        
        System.out.println(testRoute(map,arcInsp));
    }

    @Override
    public ShortestPathSolution doAlgo(Graph graph, int start, int end, ArcInspector arcInsp) {
        ShortestPathData data = new ShortestPathData(graph,graph.getNodes().get(start),graph.getNodes().get(end),arcInsp);
        AStarAlgorithm Ast = new AStarAlgorithm(data);
        ShortestPathSolution resultAst = Ast.run();
        return resultAst;
    }
    
}
