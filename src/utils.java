package panos.system;

import java.applet.Applet;
import java.net.URL;

public class utils
{
	
	// check if the given Applet is _really_ an Applet or an application
	public static boolean isApplet (Applet apl)
	{
		try {
			URL url = apl.getDocumentBase();
		}	catch (Exception e){
			return false;
		}
		return true;
	}
	
}
	
	