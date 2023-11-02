package com.nhlstenden.control;

import java.util.HashSet;

public class ControlRoom
{
    private final HashSet<Statusable> statuses;

    public ControlRoom()
    {
        this.statuses = new HashSet<>();
    }

    public void add(Statusable statusable)
    {
        this.statuses.add(statusable);
    }

    public boolean isNuclearReactorStable()
    {
        for (Statusable statusable : statuses)
        {
            if (statusable.getStatus() != Status.STABLE)
            {
                return false;
            }
        }

        return true;
    }
}
