package org.insa.graphs.gui.simple;

import org.insa.graphs.algorithm.AbstractInputData.Mode;
import org.insa.graphs.algorithm.ArcInspector;
import org.insa.graphs.model.Arc;

public class MyArcInsp implements ArcInspector{

    private final Mode mode;
    
    public MyArcInsp(Mode mode){
        this.mode = mode;
    }

    @Override
    public boolean isAllowed(Arc arc) {
        return true;
    }

    @Override
    public double getCost(Arc arc) {
        if(mode == Mode.LENGTH){
            return arc.getLength();
        }else{
            return arc.getMinimumTravelTime();
        }
        
    }

    @Override
    public int getMaximumSpeed() {
        return Integer.MAX_VALUE;
    }

    @Override
    public Mode getMode() {
        return mode;
    }
    
}
