package org.insa.graphs.model;

public class Parcour {
    private String map;
    private Graph graph;
    private Path cheminMarathon;
    private int origine;
    
    public Parcour(String map, Graph graph, int origine){
        this.map = map;
        this.graph = graph;
        this.origine = origine;
    }

    public Path createPath(){
        //faire une classe qui extend dijkstra et qui modifie find destination avec la condition que son chemin est de telle distance
        // penser a aussi rajouter la condution du vol d'oiseau
        return null;
    }
}
