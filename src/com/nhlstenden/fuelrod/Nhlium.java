package com.nhlstenden.fuelrod;

import com.nhlstenden.reactor.SplitResult;

public class Nhlium extends SplittingRod
{
    @Override
    public SplitResult split(int time, int temperature) throws MeltdownException
    {
        if (time > 60 && temperature > 100)
        {
            throw new MeltdownException();
        }

        double steam;
        double residualHeat;

        this.percentageLeft -= (0.06 * time);

        if (temperature < 90)
        {
            steam = (temperature / 70) * time * 0.9;
            residualHeat = time * 3;
        }
        else
        {
            steam = 0.9 * (temperature * 0.9 / time) * Math.sqrt(3) * time;
            residualHeat = time / 10;
        }

        if (this.percentageLeft < 0.1)
        {
            throw new MeltdownException();
        }

        return new SplitResult(steam, residualHeat);
    }
}
