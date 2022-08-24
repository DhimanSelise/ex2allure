package packages.common.localrunner;

import java.util.ArrayList;

import core.components.ComponentHandler;
import packages.common.interfaces.ifeatures;

public class ExecuteModules {
	public static void main(String[] args) {
		// RunProject();
		// RunProjectTest();
		// print(DAYS.Friday);
		// ArrList();
		// StartExecute();
		//addUser("", "");
		//System.out.println(check());
		ComponentHandler.Init();
		ComponentHandler.GetElementQuick("RAIHAN");
	}
	
	static int check() {
		int a=0, b=111;
		try {	
			System.out.println("try block");
			System.out.println("try block");
			int s = b/a;
			System.out.println("try late block");
			System.out.println(s);
			return -1;
		} catch (Exception e) {
			System.out.println("catch block");
			return 2;
		} finally {
			System.out.println("finally block");
		}
	}
	
	final static String fName = "Raihan";
	final static String lName = "Mahmud";
	final static String address = "Jhenaidah";
	final static String email = "raihan.mahamud@selise.ch";
	final static String contact = "01771442552";
	final static boolean isEmployed = true;
	final static String company = "SELISE";
	
	
	public static void addUser(String SP, String EP) {
		switch (SP) {
		case "":
			System.out.println("Start of this function.");
		if(EP.equals(fName)) return;
		case fName:
			System.out.println(fName);
		if(EP.equals(lName)) return;
		case lName:
			System.out.println(lName);
		if(EP.equals(address)) return;
		case address:
			System.out.println(address);
		if(EP.equals(email)) return;
		case email:
			System.out.println(email);
		if(EP.equals(contact)) return;
		case contact:
			System.out.println(contact);
		if(EP.equals(company)) return;
		case company:
			System.out.println(company);
			System.out.println("End of this function.");
		default:
			break;
		}
	}
	
	
	
	static void RunProjectTest() {


//		        String s = "    ";
//		        System.out.println("\"" + s + "\" - \"" + s.strip() + "\"");
//		        s = "   hello    ";
//		        System.out.println("\"" + s + "\" - \"" + s.strip() + "\"");
//		        s = "h i  ";
//		        System.out.println("\"" + s + "\" - \"" + s.strip() + "\"");

//		new StepDefination().
//		GetParameters(sentance, ParametersList);
//		int testNumber = 101;
//		String projectName = ConfigOfCLM.Default.DefaultProject;
//		String siteName = ConfigOfCLM.Default.DefaultSite;
//		String zoneName = ConfigOfCLM.Default.DefaultZone;
//		String endDate = ConfigOfCLM.Default.DefaultEndDate;
//
//		ComponentHandler.Init();
//		ComponentHandler.NavigateToURL("https://stage-clm.selise.biz/");

//		LoginModule login = new LoginModule();
//		login.getLogin("aardo@yopmail.com", "1qazZAQ!", "en");
//		
//		CheckCompatibilityOrNavigate.SelctOrganization("Do");
//
//			ProjectModule project = new ProjectModule();
//			CheckCompatibilityOrNavigate.AddProjectBtn();
//			System.out.println(Config.Project.Name);
//			System.out.println(Config.Project.Client);
//			Config.Project.Name = "RAIHAN";
//			Config.Project.Client = "ABC";
//			System.out.println(Config.Project.Name);
//			System.out.println(Config.Project.Client);
//			project.addProject("", "");
			
			
			
//			System.out.println(Config.Default.PROJECT);
//			System.out.println(Config.Project.Client);
//			Config.Project.Name = "WTF";
//			Config.Project.Client = "ABC";
//			System.out.println(Config.Default.PROJECT);
//			System.out.println(Config.Project.Client);
//			project.addProject("", "");
		
			
			
//			project.navigateToProjectMenu();
//			project.addProject(projectName, endDate, Config.Project.ClientName);
//			project.navigateToProject(projectName);
//			project.createSite(siteName, endDate, Config.Site.Address, Config.Site.Person, Config.Site.Email, Config.Site.Contact);
//	    	project.navigateToCockpitMenu();
//			project.selectSite("456");
//			
//			ZoneModule zone = new ZoneModule();
//			zone.navigateToLogisticsZoneMenu();
//			zone.addLogisticsZones(zoneName, 23, 90, false);
//			zone.addLogisticsZones("UP-1", 23, 90, false);
//			zone.addLogisticsZones("WP-1", 23, 90, false);
//			zone.addLogisticsZones("EP-1", 23, 90, false);
//			zone.addLogisticsZones("UP-2", 23, 90, false);

//		EquipmentModule eq = new EquipmentModule();
//			eq.addEquipment("E2", "Moving", "Crane");
//			eq.addEquipment("M3", "Moving", "Forklift");

//		UserModule user = new UserModule();
//		OrganizationModule org = new OrganizationModule();
//			user.navigateToTeamsMenu();
//			org.orgCreation(Config.Default.DefaultOrganization, "yopmail.com");
//			org.orgCreation("Making Memories", "yopmail.com");
			
			
			
//		MaterialModule mat = new MaterialModule();
//		mat.materialCreation("PainKiller", "pcs", "DrBD");

//			project.navigateToSite("AutoMATE-Site 101");
//			
//			ZoneSchedulingModule zs = new ZoneSchedulingModule();
//			zone.navigateToLogisticsZoneMenu();
//			zone.navigateToLogisticsZones("UP-1");
//			zs.addZoneEquipments("Brick");
//			zs.addZoneVehicles("LKW");
//			String[] time_slot = {"08:00-12:00", "13:00-14:00", "14:30-16:00", "17:00-20:00"};
//			zs.addZoneSchedule("31 Dec 2039", time_slot);

//			
//			zs.rescheduleZone(DAYS.Monday, time_slot);

//			String[] time_slot1 = {"08:00-12:00"};
//			zs.rescheduleZone(DAYS.Friday, time_slot1);

//			zs.rescheduleZone(DAYS.Friday, null);

//		ShipmentModule ship = new ShipmentModule();
//			project.navigateToSite("AutoMATE-TEST Site");
//			smartship.navigateToLogisticCard("CARD01");
//			ship.shipmentCreate("", "UP-2");

//			String mail = "test@yopmail.com";
//			user.navigateToTeamsMenu();
//			user.userCreation(mail, "Project Manager");
//			user.userCreation(mail, "Project Planner");
//			user.userCreation(mail, "Site Manager");
//			user.userCreation(mail, "Team Member");
//			user.userCreation(mail, "Team Member(Admin)");

//			user.getLogOut();
//			ComponentHandler.Sleep(20);
//			user.loInYopMail(mail);
//			login.getLogin(mail, "en", "1qazZAQ!");
//			user.createuserdetail("Raihan", "Mahamud", "01771442552");

		// ********************* Smart Shipment *********************//
//		SmartShipment smartship = new SmartShipment();
//			project.navigateToSite("Ramna Park");
//			smartship.navigateToLogisticCard("CARD01");
//			smartship.shipmentCreateStarted("16 may 2022", "A01");
//			smartship.shipmentCreateFinished(-1, "08:00 - 09:00");
	}

	
	public static void StartExecute() {
		//ComponentHandler.Init();
		//ComponentHandler.NavigateToURL("");
		ArrayList<ifeatures> features = new ArrayList<ifeatures>();
		//features.add(new login());
		//features.add(new logout());
		for (ifeatures feature : features) {
			System.out.print(feature.toString());
			if(feature.Execute("", "")) System.out.println(" is executed..");
			
		}
	}

	static void ArrList() {
		ArrayList<String> list = new ArrayList<String>();
		String sentance = "raihan";
		// String sentance = "I \"am\" \"here\" \"08:00-12:00, 13:00-14:00, 14:30-16:00,
		// 17:00-20:00\"";
		System.out.println(sentance);
		String[] parameters = sentance.split("\"");
		for (String parameter : parameters) {
			System.out.println(parameter);
		}
		System.out.println("=======================");
		for (String parameter : parameters) {
			String stripedParameter = parameter.strip();
			if (stripedParameter != "") {
				System.out.println(stripedParameter);
				list.add(stripedParameter);
			}
		}
		for (String parameter : list) {
			System.out.println(parameter);
		}
	}
	
	static void RunProject() {
//		ComponentHandler.Init();
//		ComponentHandler.NavigateToURL("http://clm.seliselocal.com");
//
//		LoginModule login = new LoginModule();
//		login.getLogin("aardo@yopmail.com", "1qazZAQ!", "en");
//		
//		CompatibilityAndNavigate.AddProjectBtn();

//		ProjectModule project = new ProjectModule();
//			project.navigateToProjectMenu();
//			project.addProject("Ramna Park", "Ramna Park", "31 Dec 2028");
//			project.navigateToProject("Ramna Park");
//			project.createSite("Ramna Park", "Moulana Bhasani Rd, Dhaka 1217, Bangladesh");
//			project.navigateToCockpitMenu();
//			project.navigateToSite("AutoMATE-TEST Site");
		//
//		ZoneModule zone = new ZoneModule();
//			zone.navigateToLogisticsZoneMenu();
//			zone.addLogisticsZones("EP-1");
//			zone.addLogisticsZones("UP-1");
//			zone.addLogisticsZones("UP-2");
		//
//		EquipmentModule eq = new EquipmentModule();
//			eq.addEquipment("E1", "Moving", "Crane");
//			
//		UserModule user = new UserModule();
//			user.navigateToTeamsMenu();
//			
//		OrganizationModule org = new OrganizationModule();
//			org.orgCreation("SELISE", "yopmail.com");
//			org.orgCreation("SOHOJ", "yopmail.com");
//			
//		MaterialModule mat = new MaterialModule();
//			mat.navigateToMaterialMenu();
//			//mat.materialCreation("Insulin", "SELISE", "m3");
//			//mat.materialCreation("Tep", "SELISE", "kg");
//			mat.materialCreation("Brick", "SOHOJ", "kg");
//			
//			zone.navigateToLogisticsZoneMenu();
//			zone.navigateToLogisticsZones("UP-2");
//			
//		ZoneSchedulingModule zs = new ZoneSchedulingModule();
//			zs.addZoneEquipments("E1");
//			zs.addZoneVehicles("LKW");
//			zs.addZoneSchedule("31 Dec 2027");
//			
//		ShipmentModule ship = new ShipmentModule();
//			ship.addlogisticsCard("AutoMATE-CARD");
//			ship.shipmentCreate("AutoMATE-CARD", "", "UP-2");
//			
//			String mail = "raihan112233@yopmail.com";
//			user.navigateToTeamsMenu();
//			user.userCreation(mail, 2);
//			user.getLogOut();
//			ComponentHandler.Sleep(20);
//			user.loInYopMail(mail);
//			login.getLogin(mail, "en", "1qazZAQ!");
//			user.createuserdetail("Raihan", "Mahamud", "01771442552");

		// ******************************* Smart Flow ******************************//
//		project.navigateToSite("Ramna Park");

//			user.navigateToTeamsMenu();
//			org.orgCreation("SELISE", "yopmail.com");
//			org.orgCreation("SOHOJ", "yopmail.com");
		//
//			mat.navigateToMaterialMenu();
//			mat.materialCreation("Insulin", "SELISE", "m2");
//			mat.materialCreation("GUM", "SELISE", "kg");
//			mat.materialCreation("Brick", "SOHOJ", "pcs");

//		SmartShipment smartship = new SmartShipment();
//			smartship.addlogisticsCard("CARD01");
//			smartship.shipmentCreate("CARD01", "", "10:00 - 11:00");
		// smartship.shipmentCreate("CARD01", "", "16 may 2022", "M1", "PKW", "E1",
		// "A01", 60, -1, "10:00 - 11:00");
	}
}