package org.insa.graphs.algorithm.shortestpath;

import java.lang.reflect.Array;
import java.util.function.IntBinaryOperator;

import org.insa.graphs.model.Node;
import org.insa.graphs.model.Label;

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

        for (index=0; index<data.getGraph().size(); index++){
            //Chercher min
            int min=Integer.MAX_VALUE;
            int labelMin;
            for (int j=0; j<data.getGraph().size(); j++){
                if (tabLabels[j].getCoutRealise()<min && !(tabLabels[j].getMarque())){
                    labelMin=j;
                    min=tabLabels[j].getCoutRealise();
                }
            }
            //Mettre a jour le tableau en iterrant sur les arc
            for (){
                tabLabels[h].getCoutRealise()=min(tabLabels[j].getCoutRealise(),tabLabels[labelMin].getCoutRealise()+);
            }
        }
    



        // when the algorithm terminates, return the solution that has been found
        return solution;
    }

}
