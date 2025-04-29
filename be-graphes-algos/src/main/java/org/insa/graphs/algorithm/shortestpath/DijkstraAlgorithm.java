package org.insa.graphs.algorithm.shortestpath;

import java.util.ArrayList;

import org.insa.graphs.algorithm.AbstractSolution.Status;
import org.insa.graphs.model.Arc;
import org.insa.graphs.model.Label;
import org.insa.graphs.model.Node;
import org.insa.graphs.model.Path;

public class DijkstraAlgorithm extends ShortestPathAlgorithm {

    public DijkstraAlgorithm(ShortestPathData data) {
        super(data);
    }

    @Override
    protected ShortestPathSolution doRun() {

        // retrieve data from the input problem (getInputData() is inherited from the
        // parent class ShortestPathAlgorithm)
        final ShortestPathData data = getInputData();

        // variable that will contain the solution of the shortest path problem
        ShortestPathSolution solution = null;

        // TODO: implement the Dijkstra algorithm
        Label [] tabLabels= new Label[data.getGraph().size()];
        //Iter 0
        int index=0;
        for(Node N: data.getGraph().getNodes()){
            tabLabels[index]=new Label(N,false,Integer.MAX_VALUE, null);
            index++;
        }
        tabLabels[data.getOrigin().getId()].setCoutRealise(0);
        index = 0;
        boolean foundDest = false;
        while (index<data.getGraph().size() && !foundDest){
            //Chercher min
            float min=Float.MAX_VALUE;
            int labelMin = -1;
            for (int j=0; j<data.getGraph().size(); j++){
                if (tabLabels[j].getCoutRealise()<min && !(tabLabels[j].getMarque())){
                    labelMin=j;
                    min=tabLabels[j].getCoutRealise();
                }
            }
            //on ajoute le minimum comme point visité
            tabLabels[labelMin].setMarque(true);
            if (labelMin == data.getDestination().getId()){
                foundDest = true;
            }
            //Mettre a jour le tableau en iterrant sur les arc
            for (Arc a : tabLabels[labelMin].getSommetCourant().getSuccessors()){
                if((tabLabels[a.getDestination().getId()].getCoutRealise())-(tabLabels[labelMin].getCoutRealise()+a.getLength())>0){
                    tabLabels[a.getDestination().getId()].setCoutRealise(tabLabels[labelMin].getCoutRealise()+a.getLength());
                    tabLabels[a.getDestination().getId()].setPere(a);
                }
            }
            index ++;
        }
        if(!foundDest){
            return new ShortestPathSolution(data, Status.INFEASIBLE);
        }
        System.out.println("index : " + index);
        System.out.println("fin algo");
        // Création de la liste de noeud du chemin
        Label fils = tabLabels[data.getDestination().getId()];
        //Label pere = tabLabels[fils.getPere().getOrigin().getId()];
        
        

        ArrayList<Arc> bestarc = new ArrayList<>();

        //bestarc.add(fils.getPere());
        //while ((pere.getSommetCourant().getId()!= data.getOrigin().getId()) ){
        while (fils.getPere()!= null ){
            System.out.println("fils : "+ fils.getSommetCourant().getId());
            bestarc.add(fils.getPere());
            fils = tabLabels[fils.getPere().getOrigin().getId()];

        }


        Path shortPath = new Path(data.getGraph(), bestarc);


        // CONTINUER AU PROCHAIN TP CRER LISTE DE NOUED


        solution = new ShortestPathSolution(data,Status.OPTIMAL,shortPath);


        System.out.println("on a finit d'ajouter wow");
        // when the algorithm terminates, return the solution that has been found
        return solution;
    }

}
