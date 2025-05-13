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

    public static float getCoutAlgo(Label l){
        float result = 0;
        if(l instanceof LabelStar){
            LabelStar lprime = (LabelStar) l;
            result = lprime.getCoutRealise()+lprime.GetEstimationCout();
        }
        else{
            result = l.getCoutRealise();
        }
        return result;
    }
    @Override
    public int compareTo(Label l){
        
        if(getCoutAlgo(l)<getCoutAlgo(this)){
            return 1;
        }else if (getCoutAlgo(l)>getCoutAlgo(this)) {
            return -1;
        }else if(getCoutAlgo((Label)l)<getCoutAlgo((Label)this)){
            return 1;
        }else if(getCoutAlgo((Label)l)>getCoutAlgo((Label)this)){
            return -1;
        }else{
            return 0;
        }

    }

}
