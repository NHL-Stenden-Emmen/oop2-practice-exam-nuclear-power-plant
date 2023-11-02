package com.nhlstenden.reactor;

import com.nhlstenden.control.Status;
import com.nhlstenden.control.Statusable;
import com.nhlstenden.fuelrod.MeltdownException;
import com.nhlstenden.fuelrod.SplittingRod;

import java.util.HashSet;
import java.util.List;

public class Reactor implements Statusable
{
    private final HashSet<SplittingRod> splittingRods;

    public Reactor()
    {
        this.splittingRods = new HashSet<>();
    }

    public void addCore(SplittingRod splittingRod)
    {
        this.splittingRods.add(splittingRod);
    }

    public SplitResult run(int time, int temperature) throws MeltdownException
    {
        // Simple solution
        //
        // double steamInCubicMeters = 0.0;
        // double residualHeat = 0.0;
        //
        // for (SplittingRod rod : this.splittingRods)
        // {
        //     SplitResult result = rod.split(temperature, time);
        //     steamInCubicMeters += result.getSteamInCubicMeters();
        //     residualHeat += result.getResidualHeat();
        // }
        //
        // return new SplitResult(steamInCubicMeters, residualHeat);

        // Advanced solution
        List<SplitResult> result = this.splittingRods.stream()
                .map(splittingRod -> {
                    try {
                        return splittingRod.split(time, temperature);
                    } catch (MeltdownException e) {
                        throw new RuntimeException(e);
                    }
                })
                .toList();

        return new SplitResult(result.stream().mapToDouble(SplitResult::getSteamInCubicMeters).sum(),
                result.stream().mapToDouble(SplitResult::getResidualHeat).sum());
    }

    @Override
    public Status getStatus()
    {
        for (SplittingRod fuel : this.splittingRods)
        {
            if (fuel.getPercentageLeft() <= 0.1)
            {
                return Status.NEEDS_ATTENTION;
            }
        }

        return Status.STABLE;
    }
}
