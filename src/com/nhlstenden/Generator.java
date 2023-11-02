package com.nhlstenden;

import com.nhlstenden.control.Status;
import com.nhlstenden.control.Statusable;

public class Generator implements Statusable
{
    private double totalProducedKwh;

    public Generator()
    {
        this.totalProducedKwh = 0;
    }

    public double generateEnergy(double steam)
    {
        this.totalProducedKwh += steam * 12;
        return this.totalProducedKwh;
    }

    @Override
    public Status getStatus()
    {
        if (this.totalProducedKwh > 556)
        {
            return Status.UNSTABLE;
        }

        return Status.STABLE;
    }
}
