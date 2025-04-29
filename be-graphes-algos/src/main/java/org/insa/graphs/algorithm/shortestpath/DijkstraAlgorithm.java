package org.insa.graphs.algorithm.shortestpath;

import java.util.ArrayList;

import org.insa.graphs.algorithm.AbstractSolution.Status;
import org.insa.graphs.algorithm.utils.BinaryHeap;
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
        
        BinaryHeap<Label> tasmarque = new BinaryHeap<>();

        BinaryHeap<Label> tasrecherche = new BinaryHeap<>();
        tasrecherche.insert(new Label(data.getOrigin(), false, 0, null));
        
        boolean foundDest = false;
        while (!foundDest){
            // dépile le tas
            Label x = tasrecherche.deleteMin(); // on enleve le min du tas

            //on rajoute le min au point marqué
            x.setMarque(true); 
            tasmarque.insert(x);
            
            if (x.getSommetCourant().getId() == data.getDestination().getId()){
                foundDest = true;
            }
            //Mettre a jour le tableau en iterrant sur les arc
            for (Arc a : x.getSommetCourant().getSuccessors()){
                //// faire la partie ou on ajoute les labels non marqué



                //if((tabLabels[a.getDestination().getId()].getCoutRealise())-(tabLabels[labelMin].getCoutRealise()+a.getLength())>0){
                  //  tabLabels[a.getDestination().getId()].setCoutRealise(tabLabels[labelMin].getCoutRealise()+a.getLength());
                    //tabLabels[a.getDestination().getId()].setPere(a);
                //}
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
