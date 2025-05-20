package org.insa.graphs.gui.simple;

import org.insa.graphs.algorithm.ArcInspector;






public class testGene {
    public static void main(String[] args) {
        testDijkstra td = new testDijkstra();

        ArcInspector arcInsp = new MyArcInsp();

        ArcInspector arcInspT = new ArcInspTime();


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

    
    }
}
