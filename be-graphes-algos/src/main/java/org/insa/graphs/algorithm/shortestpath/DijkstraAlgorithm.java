package org.insa.graphs.algorithm.shortestpath;

import org.insa.graphs.algorithm.AbstractSolution.Status;
import org.insa.graphs.model.Arc;
import org.insa.graphs.model.Graph;
import org.insa.graphs.model.Label;
import org.insa.graphs.model.Node;

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
        tabLabels[0].setCoutRealise(0);
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
                tabLabels[a.getDestination().getId()].setCoutRealise(Float.min(tabLabels[a.getDestination().getId()].getCoutRealise(),tabLabels[labelMin].getCoutRealise()+a.getLength()));
            }
            index ++;
        }
        // CONTINUER AU PROCHAIN TP CRER LISTE DE NOUED
        Graph grapheret = new Graph(null, null, null, null)
        ShortestPathData dataret = new ShortestPathData(grapheret, data.getOrigin(), data.getDestination(), null)
        ShortestPathSolution solution = new ShortestPathSolution(,Status.OPTIMAL);


        // when the algorithm terminates, return the solution that has been found
        return solution;
    }

}
