package com.nhlstenden;

import com.nhlstenden.control.ControlRoom;
import com.nhlstenden.fuelrod.MeltdownException;
import com.nhlstenden.reactor.Reactor;
import com.nhlstenden.reactor.SplitResult;

public class NuclearPowerPlant
{
    private final Reactor reactor;
    private final Generator generator;
    private final CoolingSystem coolingSystem;
    private final ControlRoom controlRoom;

    public NuclearPowerPlant()
    {
        this.controlRoom = new ControlRoom();

        this.reactor = new Reactor();
        this.controlRoom.add(this.reactor);

        this.generator = new Generator();
        this.controlRoom.add(this.generator);

        this.coolingSystem = new CoolingSystem();
        this.controlRoom.add(this.coolingSystem);
    }

    public double run(int time, int temperature) throws MeltdownException
    {
        SplitResult result = this.reactor.run(time, temperature);
        double kwh = this.generator.generateEnergy(result.getSteamInCubicMeters());
        this.coolingSystem.abductResidualHeat(result.getResidualHeat());

        return kwh;
    }
}
