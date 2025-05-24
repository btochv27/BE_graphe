package org.insa.graphs.gui.simple;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;

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
    public static void main(String[] args) throws Exception {
        testDijkstra td = new testDijkstra();

        ArcInspector arcInsp = new MyArcInsp(Mode.LENGTH);

        ArcInspector arcInspT = new MyArcInsp(Mode.TIME);

        /*
        //test basique sur la map carré
        td.dotest("/mnt/commetud/3eme Annee MIC/Graphes-et-Algorithmes/Maps/carre.mapgr",10,15, arcInsp);
        //pareil avec le temps
        td.dotest("/mnt/commetud/3eme Annee MIC/Graphes-et-Algorithmes/Maps/carre.mapgr",10,15, arcInspT);
        //test sur des point choisis aléatoirement sur la carte bordeau
        for(int i =0; i<10; i++){
            td.dotestAlea("/mnt/commetud/3eme Annee MIC/Graphes-et-Algorithmes/Maps/bordeaux.mapgr", arcInspT); //test en temps
            td.dotestAlea("/mnt/commetud/3eme Annee MIC/Graphes-et-Algorithmes/Maps/bordeaux.mapgr", arcInsp);  //test en distance
        }

        //test point non connexe
        td.dotest("/mnt/commetud/3eme Annee MIC/Graphes-et-Algorithmes/Maps/bordeaux.mapgr",6686,3397, arcInspT);

        //test chemin nul (debut = fin)
        td.dotest("/mnt/commetud/3eme Annee MIC/Graphes-et-Algorithmes/Maps/bordeaux.mapgr",1,1, arcInspT);


        //TEST SUR A star
        TestAstar ta = new TestAstar();
        //test basique sur la map carré
        ta.dotest("/mnt/commetud/3eme Annee MIC/Graphes-et-Algorithmes/Maps/carre.mapgr",10,15, arcInsp);
        //pareil avec le temps
        ta.dotest("/mnt/commetud/3eme Annee MIC/Graphes-et-Algorithmes/Maps/carre.mapgr",10,15, arcInspT);
        //test sur des point choisis aléatoirement sur la carte bordeau
        for(int i =0; i<10; i++){
            ta.dotestAlea("/mnt/commetud/3eme Annee MIC/Graphes-et-Algorithmes/Maps/bordeaux.mapgr", arcInspT); //test en temps
            ta.dotestAlea("/mnt/commetud/3eme Annee MIC/Graphes-et-Algorithmes/Maps/bordeaux.mapgr", arcInsp);  //test en distance
        }

        //test point non connexe
        ta.dotest("/mnt/commetud/3eme Annee MIC/Graphes-et-Algorithmes/Maps/bordeaux.mapgr",6686,3397, arcInspT);
        
        //test chemin nul (debut = fin)
        ta.dotest("/mnt/commetud/3eme Annee MIC/Graphes-et-Algorithmes/Maps/bordeaux.mapgr",1,1, arcInspT);


        */

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
