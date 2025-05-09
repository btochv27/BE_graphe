package org.insa.graphs.algorithm.shortestpath;

import java.util.ArrayList;
import java.util.Collections;

import org.insa.graphs.algorithm.AbstractSolution.Status;
import org.insa.graphs.algorithm.utils.BinaryHeap;
import org.insa.graphs.model.Arc;
import org.insa.graphs.model.Label;
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
        int nbNode = data.getGraph().getNodes().size();
        
        ArrayList<Label> tablabel = new ArrayList<>();
        

        for (int i =0; i<nbNode; i++){
            tablabel.add(i, new Label(data.getGraph().getNodes().get(i), false, Float.MAX_VALUE, null));
        }


        tablabel.get(data.getOrigin().getId()).setCoutRealise(0);
        BinaryHeap<Label> tasrecherche = new BinaryHeap<Label>();

        tasrecherche.insert(tablabel.get(data.getOrigin().getId()));
        notifyOriginProcessed(data.getOrigin());
        boolean foundDest = false;
        

        while (!foundDest){

            // dépile le tas
            //System.out.println(tasrecherche.toStringTree());
            Label x = tasrecherche.deleteMin(); // on enleve le min du tas
            
                //on rajoute le min au point marqué
                x.setMarque(true); 
                //System.out.println("marque cout :" + x.getCoutRealise());
                notifyNodeMarked(x.getSommetCourant());
                
                if (x.getSommetCourant().getId() == data.getDestination().getId()){
                    foundDest = true;
                    notifyDestinationReached(x.getSommetCourant());
                }
                //Mettre a jour le tableau en iterrant sur les arc
                for (Arc a : x.getSommetCourant().getSuccessors()){
                    //// faire la partie ou on ajoute les labels non marqué
                    if(!(tablabel.get(a.getDestination().getId()).getMarque())){
                        if(tablabel.get(a.getDestination().getId()).getCoutRealise() > x.getCoutRealise()+a.getLength()){
                            try {
                                tasrecherche.remove(tablabel.get(a.getDestination().getId()));
                                
                            } catch (Exception e) {
                                
                            }
                            
                            //la taille est mis à jour
                            tablabel.get(a.getDestination().getId()).setCoutRealise(x.getCoutRealise()+a.getLength());
                            tablabel.get(a.getDestination().getId()).setPere(a);
                            //System.out.println("on a ajouter a " + a.getDestination().getId() + "pere : "+x.getSommetCourant().getId());
                            notifyNodeReached(a.getDestination());

                            tasrecherche.insert(tablabel.get(a.getDestination().getId()));
                        }

                    }
                }
            
              

        }
        if(!foundDest){
            return new ShortestPathSolution(data, Status.INFEASIBLE);
        }
        
        // Création de la liste de noeud du chemin
        Label fils = tablabel.get(data.getDestination().getId());
        //Label pere = tabLabels[fils.getPere().getOrigin().getId()];
        
        

        ArrayList<Arc> bestarc = new ArrayList<>();

        //bestarc.add(fils.getPere());
        //while ((pere.getSommetCourant().getId()!= data.getOrigin().getId()) ){
        while (fils.getPere()!= null){
            //System.out.println("fils : "+ fils.getSommetCourant().getId());
            bestarc.add(fils.getPere());
            //System.out.println("fils :" + fils.getSommetCourant().getId() + " pere : " + fils.get)
            fils = tablabel.get(fils.getPere().getOrigin().getId());
            

        }
        //System.out.println(bestarc.size());
        Collections.reverse(bestarc);
        Path shortPath = new Path(data.getGraph(), bestarc);


        solution = new ShortestPathSolution(data,Status.OPTIMAL,shortPath);


        //System.out.println("on a finit d'ajouter wow");
        // when the algorithm terminates, return the solution that has been found
        return solution;
    }

}
