package com.nhlstenden;

import com.nhlstenden.control.Status;
import com.nhlstenden.control.Statusable;

public class CoolingSystem implements Statusable
{
    private double waterTemperature;

    public CoolingSystem()
    {
        this.waterTemperature = 0.0;
    }

    public void abductResidualHeat(double heat)
    {
        this.waterTemperature = heat * 0.4;
    }

    @Override
    public Status getStatus()
    {
        if (this.waterTemperature > 355.0 && this.waterTemperature < 361.0)
        {
            return Status.NEEDS_ATTENTION;
        }
        else if (this.waterTemperature > 361.0)
        {
            return Status.UNSTABLE;
        }

        return Status.STABLE;
    }
}
