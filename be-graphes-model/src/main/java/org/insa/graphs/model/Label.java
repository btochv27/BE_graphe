package org.insa.graphs.model;

public class Label implements Comparable<Label>{
    Node sommetCourant;
    boolean marque;
    float coutRealise;
    Arc pere;


    public Label(Node sommetCourant, boolean marque, float coutRealise, Arc pere) {
        this.sommetCourant = sommetCourant;
        this.marque = marque;
        this.coutRealise = coutRealise;
        this.pere = pere;
    }

    // Getters
    public Node getSommetCourant() {
        return this.sommetCourant;
    }

    public boolean getMarque() {
        return this.marque;
    }

    public float getCoutRealise() {
        return this.coutRealise;
    }

    public Arc getPere() {
        return this.pere;
    }

    // Setters
    public void setMarque(boolean marque) {
        this.marque = marque;
    }

    public void setCoutRealise(float coutRealise) {
        this.coutRealise = coutRealise;
    }

    public void setPere(Arc pere) {
        this.pere = pere;
    }

    public int compareTo(Label l){
        if (coutRealise - l.coutRealise >0){
            return 1;
        }
        else if(coutRealise == l.coutRealise){
            return 0;
        }
        return -1;
    }

}
