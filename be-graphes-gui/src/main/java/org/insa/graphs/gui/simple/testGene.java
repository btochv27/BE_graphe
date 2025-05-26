package org.insa.graphs.gui.simple;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.util.ArrayList;

import org.insa.graphs.algorithm.AbstractInputData.Mode;
import org.insa.graphs.algorithm.ArcInspector;
import org.insa.graphs.algorithm.shortestpath.ParcourMara;
import org.insa.graphs.algorithm.shortestpath.ShortestPathData;
import org.insa.graphs.gui.drawing.Drawing;
import org.insa.graphs.model.Graph;
import org.insa.graphs.model.Path;
import org.insa.graphs.model.io.BinaryGraphReader;
import org.insa.graphs.model.io.GraphReader;






public class testGene {

    static int getRandInt(int min, int max){
        return (int)((Math.random()*(max-min+1))+min);
    }

    public static void main(String[] args) throws Exception {
        testDijkstra td = new testDijkstra();

        ArcInspector arcInsp = new MyArcInsp(Mode.LENGTH);

        ArcInspector arcInspT = new MyArcInsp(Mode.TIME);
        
        String map="/mnt/commetud/3eme Annee MIC/Graphes-et-Algorithmes/Maps/bordeaux.mapgr";
        String map_bret="/mnt/commetud/3eme Annee MIC/Graphes-et-Algorithmes/Maps/bretagne.mapgr";
        String map_carre="/mnt/commetud/3eme Annee MIC/Graphes-et-Algorithmes/Maps/carre.mapgr";

        //test basique sur la map carré
        td.dotest(map_carre,10,15, arcInsp);
        //pareil avec le temps
        td.dotest(map_carre,10,15, arcInspT);
        //test sur des point choisis aléatoirement sur la carte bordeau
        for(int i =0; i<10; i++){
            td.dotestAlea("/mnt/commetud/3eme Annee MIC/Graphes-et-Algorithmes/Maps/bordeaux.mapgr", arcInspT); //test en temps
            td.dotestAlea("/mnt/commetud/3eme Annee MIC/Graphes-et-Algorithmes/Maps/bordeaux.mapgr", arcInsp);  //test en distance
        }

        //test point non connexe
        td.dotest(map,6686,3397, arcInspT);

        //test chemin nul (debut = fin)
        td.dotest(map,1,1, arcInspT);

        //TEST SUR A star
        TestAstar ta = new TestAstar();
        //test basique sur la map carré
        ta.dotest(map_carre,10,15, arcInsp);
        //pareil avec le temps
        ta.dotest(map_carre,10,15, arcInspT);
        //test sur des point choisis aléatoirement sur la carte bordeau
        for(int i =0; i<10; i++){
            ta.dotestAlea(map, arcInspT); //test en temps
            ta.dotestAlea(map, arcInsp);  //test en distance
        }
        //test point non connexe
        ta.dotest(map,6686,3397, arcInspT);
        
        //test chemin nul (debut = fin)
        ta.dotest(map,1,1, arcInspT);


        //TESTS TEMPS ASTAR ET DIJKSTRA
        ArrayList<Float> tab_res=new ArrayList<Float>(10);
        float total=0;
        for (int i=0; i<10; i++){
            int origine = getRandInt(1, 300000);
            int destination = getRandInt(1, 300000);

            long temps_start = System.currentTimeMillis();
            td.doAlgo(td.createGraph(map_bret),origine,destination, arcInsp);
            long temps_end = System.currentTimeMillis();
            long delta_dij=temps_end-temps_start;
            System.out.println("Temps Dijkstra en ms :" + delta_dij);

            temps_start = System.currentTimeMillis();
            ta.doAlgo(td.createGraph(map_bret),origine,destination, arcInsp);
            temps_end = System.currentTimeMillis();
            long delta_astar=temps_end-temps_start;
            System.out.println("Temps A-Star en ms :" + delta_astar);
            float res=(float)((1.0-(((float)delta_astar)/((float)delta_dij)))*100.0);
            tab_res.add(res);
            System.out.println("Pourcentage d'amélioration :" + res);
            total=total+res;
        }
        System.out.println("Moyenne : "+ total/10.0);

        //TEST DU MARATHON
        
        // create the drawing
        final Drawing drawing = Launch.createDrawing();

        final Graph graph;
        final Path path;

        // create a graph reader
        try (final GraphReader reader = new BinaryGraphReader(new DataInputStream(
                new BufferedInputStream(new FileInputStream("/mnt/commetud/3eme Annee MIC/Graphes-et-Algorithmes/Maps/bretagne.mapgr"))))) {

            graph = reader.read();
        }

        drawing.drawGraph(graph);

        

        ShortestPathData data = new ShortestPathData(graph,graph.getNodes().get(212168),graph.getNodes().get(101),arcInsp);
        
        //Point qui ont été verifier et marche : 212168 / 100 / 1
        ParcourMara parcour = new ParcourMara(data); //on peut indiquer une distance afin de réaliser un parcour d'une distance autre que celle d'un marathon.

        path = parcour.createPath();



        drawing.drawPath(path);
    }
}
