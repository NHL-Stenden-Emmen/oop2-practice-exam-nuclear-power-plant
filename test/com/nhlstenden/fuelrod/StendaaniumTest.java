package com.nhlstenden.fuelrod;

import com.nhlstenden.reactor.SplitResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StendaaniumTest
{
    private Stendaanium stendaanium;

    @BeforeEach
    void setup()
    {
        this.stendaanium = new Stendaanium();
    }

    @Test
    void split_below50_noDecrease() throws MeltdownException
    {
        SplitResult result = this.stendaanium.split(1, 49);

        assertEquals(100.0, this.stendaanium.getPercentageLeft());
        assertEquals(2450.0, result.getSteamInCubicMeters(), 0.001);
        assertEquals(40.0, result.getResidualHeat(), 0.001);
    }

    @Test
    void split_exact50_decrease() throws MeltdownException
    {
        SplitResult result = this.stendaanium.split(1, 50);

        assertEquals(99.97, this.stendaanium.getPercentageLeft(), 0.1);
        assertEquals(2500.0, result.getSteamInCubicMeters(), 0.1);
        assertEquals(40.0, result.getResidualHeat(), 0.1);
    }

    @Test
    void split_exact150_noMeltdown()
    {
        assertDoesNotThrow(() -> {
            this.stendaanium.split(1, 150);
        });
    }

    @Test
    void split_above150_meltdown()
    {
        assertThrows(MeltdownException.class, () -> {
            this.stendaanium.split(1, 151);
        });
    }
}