package com.nhlstenden.reactor;

public class SplitResult
{
    private final double steamInCubicMeters;
    private final double residualHeat;

    public SplitResult(double steamInCubicMeters, double residualHeat)
    {
        this.steamInCubicMeters = steamInCubicMeters;
        this.residualHeat = residualHeat;
    }

    public double getSteamInCubicMeters()
    {
        return this.steamInCubicMeters;
    }

    public double getResidualHeat()
    {
        return this.residualHeat;
    }
}
