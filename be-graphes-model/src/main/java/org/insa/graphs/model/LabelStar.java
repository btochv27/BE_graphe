package org.insa.graphs.model;

public class LabelStar extends Label{
    private float estimationCout;


    public LabelStar(Node sommetCourant, boolean marque, float coutRealise, Arc pere, float estimationCout){
        super(sommetCourant,marque,coutRealise, pere);
        this.estimationCout=estimationCout;

    }

    public float GetEstimationCout(){
        return estimationCout;
    }

    public void SetEstimationCout(float estimationCout){
        this.estimationCout=estimationCout;
    }

    public int compareTo(LabelStar l){
        if(l.getCoutRealise()+l.GetEstimationCout()<this.coutRealise+this.estimationCout){
            return 1;
        }else if (l.getCoutRealise()+l.GetEstimationCout()>this.coutRealise+this.estimationCout) {
            return -1;
        }else if(l.getCoutRealise()<this.coutRealise){
            return 1;
        }else if(l.getCoutRealise()>this.coutRealise){
            return -1;
        }else{
            return 0;
        }

    }
}
