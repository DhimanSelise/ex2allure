package com.costa.luiz.testng;

import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import pcx.modules.LoginModule;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import core.components.ComponentHandler;

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
    @Feature("Login on IPEX")
    @Test(priority = 1)
    public void Login() {
        //Dummy code
    	System.out.println("testing now...");
		ComponentHandler.Init(); 
		ComponentHandler.NavigateToURL("http://pcx.seliselocal.com/");
		System.out.println("Site Visited");
		new LoginModule().getLogin("kawsar.ahmed@selise.ch", "en", "1qazZAQ!");
	    System.out.println("Login Worked");
        boolean condition = true;
        Assert.assertTrue(condition);
    }

    @Feature("Entertainment")
    @Severity(SeverityLevel.MINOR)
    @Test(priority = 2, enabled = true)
    public void entertainmentSystem() {
        //Dummy code
    	System.out.println("EnterTainment");
    }
    
    

}
