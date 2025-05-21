package org.insa.graphs.algorithm.shortestpath;

import java.util.ArrayList;
import java.util.Collections;

import org.insa.graphs.algorithm.AbstractInputData.Mode;
import org.insa.graphs.algorithm.AbstractSolution.Status;
import org.insa.graphs.algorithm.utils.BinaryHeap;
import org.insa.graphs.algorithm.utils.ElementNotFoundException;
import org.insa.graphs.model.Arc;
import org.insa.graphs.model.Label;
import org.insa.graphs.model.LabelStar;
import org.insa.graphs.model.Path;

public class DijkstraAlgorithm extends ShortestPathAlgorithm {

    private int idNodeStart;
    private int idNodeEnd;
    public DijkstraAlgorithm(ShortestPathData data) {
        super(data);
        this.idNodeStart = data.getOrigin().getId();
        this.idNodeEnd = data.getDestination().getId();
    }

    public DijkstraAlgorithm(ShortestPathData data, int nodeStart) {
        super(data);
        this.idNodeStart = nodeStart;
        this.idNodeEnd = data.getDestination().getId();
    }

    public DijkstraAlgorithm(ShortestPathData data, int idNodeStart2, int idNodeEnd) {
        super(data);
        this.idNodeStart = idNodeStart2;
        this.idNodeEnd = idNodeEnd;
    }

    protected void Iteration(Label x,ArrayList<Label> tablabel,BinaryHeap<Label> tasrecherche,ShortestPathData data){
        for (Arc a : x.getSommetCourant().getSuccessors()){
               
                if(!(tablabel.get(a.getDestination().getId()).getMarque())){
                    double w = data.getCost(a);
                    if(tablabel.get(a.getDestination().getId()).getCoutRealise() > x.getCoutRealise()+(float)w){
                        try {
                            tasrecherche.remove(tablabel.get(a.getDestination().getId()));
                            
                        } catch (ElementNotFoundException e) {
                            notifyNodeReached(a.getDestination());
                        }
                        
                        //la taille est mis à jour
                        tablabel.get(a.getDestination().getId()).setCoutRealise(x.getCoutRealise()+(float)w);
                        tablabel.get(a.getDestination().getId()).setPere(a);
                        //verification si on est dans la partie Astar
                        if(this instanceof AStarAlgorithm){

                            org.insa.graphs.model.Point p_dest = tablabel.get(idNodeEnd).getSommetCourant().getPoint();
                            org.insa.graphs.model.Point p_cur = tablabel.get(a.getDestination().getId()).getSommetCourant().getPoint();
                            float estimate_cost = (float) org.insa.graphs.model.Point.distance(p_cur,p_dest);
                            LabelStar labelmodif = (LabelStar)tablabel.get(a.getDestination().getId());
                            if(data.getMode()==Mode.TIME){
                                estimate_cost= (float) ((estimate_cost*3.6)/150.0);

                            }
                            labelmodif.SetEstimationCout(estimate_cost);
                            tablabel.set(a.getDestination().getId(), labelmodif);
                        }
                        //System.out.println("on insere !" + tasrecherche.size());
                        tasrecherche.insert(tablabel.get(a.getDestination().getId()));
                    }

                }
            }
    }

    protected ShortestPathSolution CreateSolution(ShortestPathData data, ArrayList<Label> tablabel,int idend){
             
        // Création de la liste de noeud du chemin
        Label fils = tablabel.get(idend);
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


        return new ShortestPathSolution(data,Status.OPTIMAL,shortPath);
    }

    protected boolean findDestination(BinaryHeap<Label> tasrecherche, ShortestPathData data, ArrayList<Label> tablabel){
        boolean result = false;
        
        while (!result && !tasrecherche.isEmpty()){
            
            //System.out.println("On cherche");
            // dépile le tas
            Label x = tasrecherche.deleteMin(); // on enleve le min du tas
            
            //System.out.println("on retire !" + tasrecherche.size() + " node :" + x.getSommetCourant().getId());
            //on rajoute le min au point marqué
            x.setMarque(true); 
            notifyNodeMarked(x.getSommetCourant());
            
            result = isFinished(x,data);
            
            //Mettre a jour le tableau en iterrant sur les arc
            Iteration(x, tablabel, tasrecherche,data);
            
        }
        return result;
    }

    protected boolean isFinished(Label x, ShortestPathData data) {
        boolean result =false;
        if (x.getSommetCourant().getId() == idNodeEnd){
            result = true;
            notifyDestinationReached(x.getSommetCourant());
        }
        return result;
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


        tablabel.get(idNodeStart).setCoutRealise(0);
        BinaryHeap<Label> tasrecherche = new BinaryHeap<Label>();

        tasrecherche.insert(tablabel.get(idNodeStart));
        notifyOriginProcessed(tablabel.get(idNodeStart).getSommetCourant());

        boolean foundDest = findDestination(tasrecherche,data,tablabel);
        
        if (this instanceof DijkDistAlgo){
            return CreateSolution(data,tablabel,idNodeEnd);
        }
        if(!foundDest || (tablabel.get(idNodeEnd).getPere() == null)){ //cas ou les point ne sont pas connexe ou si il n'y a pas de chemin (debut = fin)
            
            return new ShortestPathSolution(data, Status.INFEASIBLE);
            
        }

        solution = CreateSolution(data,tablabel,idNodeEnd);
        //System.out.println("on a finit d'ajouter wow");
        // when the algorithm terminates, return the solution that has been found
        return solution;
    }

}
