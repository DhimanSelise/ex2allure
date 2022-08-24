package pcx.modules;

import core.components.ComponentHandler;

public class Login {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Visit();
		getLogin();

	}
	
	public static void Visit() {
		System.out.println("testing now");
		ComponentHandler.Init(); 
		ComponentHandler.NavigateToURL("http://pcx.seliselocal.com/");
	}
	
	public static void getLogin() {
		System.out.println("-- Login Started --");
		ch.Send("#am-1218", "kawsar.ahmed@selise.ch");
		ch.Send("#am-1221", "1qazZAQ!");
		ch.Click("#am-1226");
		ch.ClickByText("LATER");
	}

}
