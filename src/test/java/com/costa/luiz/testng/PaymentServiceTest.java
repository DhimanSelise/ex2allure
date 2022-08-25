package com.costa.luiz.testng;

import io.qameta.allure.Feature;
import pcx.modules.myClass;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static java.util.Objects.nonNull;

@Feature("Smoke Test")
public class PaymentServiceTest {

    @Feature("Create A Case then Edit")
    @Test(groups = {"sendTxn"})
    void CreateThenEditCase() {
    	System.out.println("$_ Running Create A Case then Edit");
    	new myClass().CreateCase();
    }

    @Feature("Issuing Bank")
    @Test(groups = {"sendTxn"})
    void validateTransaction() {
        // Dummy test
        String transactionId = "42";
        Assert.assertNotNull(transactionId);
    }

    @Feature("Issuing Bank")
    @Parameters({"42"})
    @Test(dependsOnGroups = {"sendTxn"})
    void rollbackATransaction(@Optional("00") String transaction) {
        // Dummy test
        Assert.assertTrue(nonNull(transaction));
    }

    @Feature("Internal system")
    @Test(dataProvider = "messageProvider")
    void sendAMessage(String type, String content) {
        // Dummy test
        Assert.assertTrue(nonNull(type) && content.startsWith("Payment"));
    }

    @Feature("Communication")
    @Test
    void sendCommunication() {
        // Dummy test
        Assert.assertNotNull("New loan available");
    }

    @DataProvider(name = "messageProvider")
    public Object[][] createMessages() {
        return new Object[][]{
                {"Success", "Payment accepted"},
                {"Failure", "Payment not accepted"},
        };
    }

    @Test
    void dontFixMe() {
        Assert.assertTrue(1994 > 2002);
    }

}
