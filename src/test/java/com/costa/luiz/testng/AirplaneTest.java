package com.costa.luiz.testng;

import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static java.util.Objects.nonNull;

public class AirplaneTest {

    @Severity(SeverityLevel.BLOCKER)
    @Feature("Engine")
    @Test(dataProvider = "engineProvider")
    public void engine(String engine, int power) {
        //Dummy code
        Assert.assertTrue(nonNull(engine) && power > 90);
    }

    @DataProvider(name = "engineProvider")
    public Object[][] createEngines() {
        return new Object[][]{
                {"Rolls-Royce", 100},
                {"General Electric", 99},
                {"Pratt & Whitney", 200},
        };
    }


    @Severity(SeverityLevel.BLOCKER)
    @Feature("Safety")
    @Test(priority = 1)
    public void brakes() {
        //Dummy code
        boolean condition = true;
        Assert.assertTrue(condition);
    }

    @Feature("Entertainment")
    @Severity(SeverityLevel.MINOR)
    @Test(priority = 2, enabled = false)
    public void entertainmentSystem() {
        //Dummy code
        int coverage = 100;
        Assert.assertEquals(coverage, coverage);
    }

}
