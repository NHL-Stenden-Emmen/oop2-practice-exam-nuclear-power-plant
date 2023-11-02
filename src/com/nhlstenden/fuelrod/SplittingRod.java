package com.nhlstenden.fuelrod;

import com.nhlstenden.reactor.SplitResult;

public abstract class SplittingRod
{
    protected double percentageLeft;

    public SplittingRod()
    {
        this.percentageLeft = 100.0;
    }

    public double getPercentageLeft()
    {
        return this.percentageLeft;
    }

    public abstract SplitResult split(int time, int temperature) throws MeltdownException;
}
