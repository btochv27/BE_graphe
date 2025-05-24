package org.insa.graphs.algorithm.shortestpath;

import org.insa.graphs.model.Path;

public class ParcourMara {
    
    private ShortestPathData data;
    private double distanceTot;
    private boolean isConvaincant = false;
    private double deltaFind =0;


    public ParcourMara(ShortestPathData data){
        this.data = data;
        this.distanceTot = 42195; // A MODIFIER as la vrai distance d'un marathon 
    }

    public ParcourMara(ShortestPathData data,double distancemara){
        this.data = data;
        this.distanceTot = distancemara;
    }

    public Path createPath(){
        //faire une classe qui extend dijkstra et qui modifie find destination avec la condition que son chemin est de telle distance
        // penser a aussi rajouter la condution du vol d'oiseau
        System.out.println("on créé un marathon !");

        // on créé le premier coté du triangle equilateral
        DijkDistAlgo dij = new DijkDistAlgo(data,distanceTot/3);
        ShortestPathSolution resultDijDist = dij.run();
        Path path = resultDijDist.getPath();

        System.out.println("distance premier coté " + path.getLength());
        Path resultPath = path;
        while(!isConvaincant){
            // on créé le deuxieme cotes
            
            System.out.println("delta " + deltaFind);
            DijkDistAlgo dij2 = new DijkDistAlgo(data,distanceTot/3,(distanceTot/3)+deltaFind,path.getOrigin().getPoint(),path.getDestination().getId());
            ShortestPathSolution resultDijDist2 = dij2.run();
            Path path2 = resultDijDist2.getPath();

            System.out.println("distance 2 coté " + path2.getLength());
            // on relie la fin du path 2 au path 1

            DijkstraAlgorithm dij3 = new DijkstraAlgorithm(data,path2.getDestination().getId(),path.getOrigin().getId());
            ShortestPathSolution resultDijDist3 = dij3.run();
            Path path3 = resultDijDist3.getPath();

            System.out.println("distance 3 coté " + path3.getLength());
            resultPath = Path.concatenate(path,path2,path3);
            System.out.println("distance concatenate " + resultPath.getLength());
            //System.out.println(resultPath.getLength() + " delta = "+deltaFind);
            if(abs(resultPath.getLength()-distanceTot)<(distanceTot/100)|| abs(deltaFind)>distanceTot/3){ //on s'accorde 1% d'erreur
                isConvaincant = true;
                resultPath = Path.concatenate(path,path2,path3);
            }else{
                if(resultPath.getLength()-distanceTot < 0){
                    // on a un chemin trop court
                    if(deltaFind <0){
                        System.out.println("erreur on va tourner en boucle");
                        isConvaincant = true;
                        resultPath = Path.concatenate(path,path2,path3);
                    }
                    deltaFind = deltaFind+(distanceTot/(30)); 
                }else{
                    if(deltaFind >0){
                        System.out.println("erreur on va tourner en boucle");
                        isConvaincant = true;
                        resultPath = Path.concatenate(path,path2,path3);
                    }
                    deltaFind = deltaFind-(distanceTot/(30)); 
                }

            }
        }

        System.out.println("Distance final du parcours :" + resultPath.getLength());
        return resultPath;
    }

    private double abs(double f) {
        if(f<0){
            return f*(-1);
        }
        return f;
    }
}
