package com.nhlstenden.fuelrod;

import com.nhlstenden.reactor.SplitResult;

public class Stendaanium extends SplittingRod
{
    @Override
    public SplitResult split(int time, int temperature) throws MeltdownException
    {
        if (temperature > 150)
        {
            throw new MeltdownException();
        }

        if (temperature >= 50)
        {
            this.percentageLeft -= 0.00007 * temperature * time + 0.0004;
        }

        return new SplitResult(50.0 * temperature, (time / 0.5) * 20.0);
    }
}
