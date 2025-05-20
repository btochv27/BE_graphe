package org.insa.graphs.gui.simple;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

import org.insa.graphs.algorithm.ArcInspector;
import org.insa.graphs.algorithm.shortestpath.BellmanFordAlgorithm;
import org.insa.graphs.algorithm.shortestpath.ShortestPathData;
import org.insa.graphs.algorithm.shortestpath.ShortestPathSolution;
import org.insa.graphs.model.Graph;
import org.insa.graphs.model.io.BinaryGraphReader;
import org.insa.graphs.model.io.GraphReader;

public abstract class testAlgoChemin {
    public abstract ShortestPathSolution doAlgo(Graph graph,int start, int end,ArcInspector arcInsp);

    public int getRandInt(int min, int max){
        return (int)((Math.random()*(max-min+1))+min);
}

    public Graph createGraph(final String mapName){

        Graph graph = null;
        // create a graph reader
        try (final GraphReader reader = new BinaryGraphReader(new DataInputStream(new BufferedInputStream(new FileInputStream(mapName))))) {

            graph = reader.read();
        } catch (IOException ex) {
            System.out.println("probleme lecture graph");
        }
        return graph;
    }

    public boolean testRoute(String map,ArcInspector arcInsp){
        Graph graphe = createGraph(map);
        int start = getRandInt(1, graphe.size());

        int end = getRandInt(1, graphe.size());
        return testRoute(map,start, end, arcInsp);
    }

    public boolean testRoute(String map,int start, int end,ArcInspector arcInsp){
        boolean resultb = false;
       // visit these directory to see the list of available files on commetud.
        final String mapName =map;
        

        Graph graph = createGraph(mapName);
        
        // on recupere le resultat de l'algo
        ShortestPathSolution result = doAlgo(graph,start,end,arcInsp);
        // on recupere le resultat de bellman ford qui est de confiance
        ShortestPathData data = new ShortestPathData(graph,graph.getNodes().get(start),graph.getNodes().get(end),arcInsp);
        BellmanFordAlgorithm bellman = new BellmanFordAlgorithm(data);
        ShortestPathSolution resultBellman = bellman.run();
        //on regarde si le resultat est cohérant

        //System.out.println(result.getPath().getLength());
        //System.out.println(resultBellman.getPath().getLength());

        boolean isConexe = true;
        //on regarde si on trouve un chemin
        if(!resultBellman.isFeasible()){
            isConexe = false;
            System.out.println("chemin non connexe entre " + start +" et " + end);
            if(!result.isFeasible()){
                resultb = true;
            }
            
        }

        //on regarde si le resultat est cohérant
        if(isConexe && abs((result.getPath().getLength()-resultBellman.getPath().getLength()))<0.5){
            resultb = true;
        }
        return resultb;
    }

    protected double abs(float f){
        if (f<0){
            return (f*-1);
        }
        return f;
    }
}
