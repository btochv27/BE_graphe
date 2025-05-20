package org.insa.graphs.gui.simple;



import org.insa.graphs.algorithm.ArcInspector;
import org.insa.graphs.algorithm.shortestpath.DijkstraAlgorithm;
import org.insa.graphs.algorithm.shortestpath.ShortestPathData;
import org.insa.graphs.algorithm.shortestpath.ShortestPathSolution;
import org.insa.graphs.model.Graph;

public class testDijkstra extends testAlgoChemin{
    //classe réalisant les test de dijkstra

   

    public void dotest(String map, int start, int end, ArcInspector arcInsp){
        System.out.println("TEST dijkstra");

        System.out.println(testRoute(map, start, end,arcInsp));
    }

    public void dotestAlea(String map, ArcInspector arcInsp){
        System.out.println("TEST dijkstra");
        
        System.out.println(testRoute(map,arcInsp));
    }

    @Override
    public ShortestPathSolution doAlgo(Graph graph,int start,int end,ArcInspector arcInsp) {
        
        ShortestPathData data = new ShortestPathData(graph,graph.getNodes().get(start),graph.getNodes().get(end),arcInsp);
        DijkstraAlgorithm dij = new DijkstraAlgorithm(data);
        ShortestPathSolution resultDij = dij.run();
        return resultDij;
        
    }
}
