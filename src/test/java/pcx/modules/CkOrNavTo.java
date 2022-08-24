package pcx.modules;

import core.components.ComponentHandler;
import pcx.commons.Locators;

public class CkOrNavTo {

	public static void ExpertsActors() {
		SendRequestBtn();
	}
	public static void SendRequestBtn() {
		if (ComponentHandler.WaitForElementVisibility("#", 5))
			return;

		ComponentHandler.Click("#");
		ComponentHandler.WaitForElementVisibility("#", 30);
	}
	
	
	
	
//	private void uploadImages(String imageName) {
//	ComponentHandler.UploadFile("C:\\Users\\raiha\\Downloads\\STUDIES\\Img", imageName);
//	ComponentHandler.Click("//span[contains(text(),'UPLOAD PHOTO')]");
//	ComponentHandler.WaitForElementVisibility("#image-coropper-save", 30);
//	ComponentHandler.ExecuteScript("document.querySelector('.upload-drop-zone.ng-star-inserted').click()");
//	ComponentHandler.Click(".upload-drop-zone.ng-star-inserted");
//	ComponentHandler.WaitForElementVisibility("#inputImg", 30);
//	ComponentHandler.Click("#image-coropper-save");
//}
}