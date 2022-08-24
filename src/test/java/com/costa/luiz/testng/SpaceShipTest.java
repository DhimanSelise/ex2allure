package com.costa.luiz.testng;


import io.qameta.allure.Feature;
import org.testng.Assert;
import org.testng.annotations.Test;

@Feature("Imperial to Metric")
public class SpaceShipTest {

    @Test(description = "Check the measure to be metric")
    void checkMeasureToBeMetric() {
        double yard = 5d;
        double meter = 4.572;
        // Call the service to calculate etc...
        Assert.assertEquals(convertToMeter(yard), meter);
    }

    private double convertToMeter(double yard) {
        // Don't do this at home, this should be outside the test class
        return yard * 0.9144d;
    }
}
