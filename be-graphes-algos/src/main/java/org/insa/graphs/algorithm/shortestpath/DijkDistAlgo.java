package org.insa.graphs.algorithm.shortestpath;

import java.util.ArrayList;

import org.insa.graphs.model.Label;
import org.insa.graphs.model.Point;

public class DijkDistAlgo extends DijkstraAlgorithm{

    private double distance;
    private int destination;
    private boolean isDistanceVol;
    private double distVol;
    private Point pointVol;

    public DijkDistAlgo(ShortestPathData data, double distance) {
        super(data);
        this.distance=distance;
        this.isDistanceVol = false;
        this.distVol = 0;
    }

    public DijkDistAlgo(ShortestPathData data, double distance, double distVol,Point pointVol,int idNodeStart) {
        super(data,idNodeStart);
        this.distance=distance;
        this.isDistanceVol = true;
        this.distVol = distVol;
        this.pointVol = pointVol;
    }



    @Override protected boolean isFinished(Label x, ShortestPathData data){
        boolean result =false;
        //System.out.println(x.getCoutRealise());
        if(isDistanceVol){
            
            if((abs(x.getCoutRealise()-distance)<(distance/20))){
                //System.out.println(org.insa.graphs.model.Point.distance(x.getSommetCourant().getPoint(),pointVol) + " | "+ distVol);

                //System.out.println(abs(org.insa.graphs.model.Point.distance(x.getSommetCourant().getPoint(),pointVol)- distVol));
                
                if( abs((double)(org.insa.graphs.model.Point.distance(x.getSommetCourant().getPoint(),pointVol)-distVol))<(distance/20)){ //on s'accord 5% d'erreur
                System.out.println("fin du dij avec distance vol" + org.insa.graphs.model.Point.distance(x.getSommetCourant().getPoint(),pointVol) + " | "+ distVol);
                result = true;

                this.destination = x.getSommetCourant().getId();
                notifyDestinationReached(x.getSommetCourant());
                }
            }
            
            
        }else if (x.getCoutRealise()>distance){
            System.out.println("fin du dij");
            result = true;

            this.destination = x.getSommetCourant().getId();
            notifyDestinationReached(x.getSommetCourant());
        }
    
        return result;
    }

    @Override protected ShortestPathSolution CreateSolution(ShortestPathData data, ArrayList<Label> tablabel,int idend){

        //System.out.println("création du path" + destination);
        return super.CreateSolution(data, tablabel, destination);
    }

    private double abs(double f) {
        if(f<0){
            return f*(-1);
        }
        return f;
    }
}
