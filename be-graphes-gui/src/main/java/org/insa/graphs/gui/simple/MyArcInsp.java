package org.insa.graphs.gui.simple;

import org.insa.graphs.algorithm.AbstractInputData.Mode;
import org.insa.graphs.algorithm.ArcInspector;
import org.insa.graphs.model.Arc;

public class MyArcInsp implements ArcInspector{

    @Override
    public boolean isAllowed(Arc arc) {
        return true;
    }

    @Override
    public double getCost(Arc arc) {
        return arc.getLength();
    }

    @Override
    public int getMaximumSpeed() {
        return Integer.MAX_VALUE;
    }

    @Override
    public Mode getMode() {
        return Mode.LENGTH;
    }
    
}
