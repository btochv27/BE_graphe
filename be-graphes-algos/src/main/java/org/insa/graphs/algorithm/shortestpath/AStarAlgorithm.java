package org.insa.graphs.algorithm.shortestpath;

import java.util.ArrayList;

import org.insa.graphs.algorithm.AbstractSolution.Status;
import org.insa.graphs.algorithm.utils.BinaryHeap;
import org.insa.graphs.model.Label;
import org.insa.graphs.model.LabelStar;


public class AStarAlgorithm extends DijkstraAlgorithm {

    public AStarAlgorithm(ShortestPathData data) {
        super(data);
    }


    protected ShortestPathSolution doRun() {
        // retrieve data from the input problem (getInputData() is inherited from the
        // parent class ShortestPathAlgorithm)
        final ShortestPathData data = getInputData();

        // variable that will contain the solution of the shortest path problem
        ShortestPathSolution solution = null;

        //INITIALISATION
        int nbNode = data.getGraph().getNodes().size();
        
        ArrayList<Label> tablabel = new ArrayList<>();
        
        org.insa.graphs.model.Point p_dest = data.getDestination().getPoint();
        for (int i =0; i<nbNode; i++){
            org.insa.graphs.model.Point p_cur = data.getGraph().getNodes().get(i).getPoint();
            float estimate_cost = (float) org.insa.graphs.model.Point.distance(p_cur,p_dest);

            tablabel.add(i, new LabelStar(data.getGraph().getNodes().get(i), false, Float.MAX_VALUE, null,estimate_cost));
        }


        tablabel.get(data.getOrigin().getId()).setCoutRealise(0);
        BinaryHeap<Label> tasrecherche = new BinaryHeap<>();

        tasrecherche.insert(tablabel.get(data.getOrigin().getId()));
        notifyOriginProcessed(data.getOrigin());
        //on effectue l'algo pour trouver la destination
        boolean foundDest = findDestination(tasrecherche, data, tablabel);
        
        if(!foundDest){
            return new ShortestPathSolution(data, Status.INFEASIBLE);
        }
        
        solution = CreateSolution(data, tablabel);
        return solution;
    }
}
