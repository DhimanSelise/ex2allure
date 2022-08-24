package com.costa.luiz.testng;

import static java.util.Objects.nonNull;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import core.components.ComponentHandler;
import io.qameta.allure.Feature;
import pcx.modules.LoginModule;
import pcx.modules.myClass;
@Feature("IPEX Test Automaion")
public class IPEXTesting {
	@Feature("Login")
	@Test()
	void JustLogin() {
		System.out.println("testing now...");
		ComponentHandler.Init(); 
		ComponentHandler.NavigateToURL("http://pcx.seliselocal.com/");
		System.out.println("Site Visited");
		new LoginModule().getLogin("kawsar.ahmed@selise.ch", "en", "1qazZAQ!");
	    System.out.println("Login Worked");
	}

	@Feature("Create Case")
	@Test()
	void CreateCase() {
		// Dummy test
		new myClass().CreateCase();
	}


	@Test
	void ITSM() {
		Assert.assertTrue(1994 > 2002);
	}
	
	@Test
	void MobilityAndLogistics() {
		Assert.assertTrue(true);
	}
}
