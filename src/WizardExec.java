// ******************************************************************************
// WizardExec.java:	Application
//  This application demostrates the use of the wizard class
// ******************************************************************************
import java.awt.*;
import java.applet.Applet;
import panos.awt.browseButton;
import panos.awt.Wizard;
import panos.system.utils;

//==============================================================================
// Main Class for applet WizardExec
//
//==============================================================================
public class WizardExec extends Applet
{

	TextField tfx;
	browseButton bb;
	Choice mc ;
	
	Wizard myWiz;
	String tempOut;

	// The following code is in order to run this program as application too
	public WizardExec (){}
	public static void main(String argv[]) 
	{
		WizardExec we = new WizardExec ();
		we.start();
	}

	public void paint (Graphics g)
	{
		g.drawString (tempOut, 12, 12);
	}


	public boolean handleEvent (Event ev)
	{

		if (ev.id == myWiz.EventId)
		{
			tempOut = mc.getSelectedItem() + " - " + tfx.getText() + " - " +bb.getFilename ();
			if (!utils.isApplet(this)) System.exit(0);
			repaint();
			return true;
		}
		else if (ev.id == (myWiz.EventId + 1))
		{
			tempOut = "Cancel clicked.";
			if (!utils.isApplet(this)) System.exit(0);
			repaint();
			return true;
		}
		return false;
	}


	public void start()
	{
		tempOut = "";

		myWiz = new Wizard(this , 500, 500);
		
		mc= new Choice();
		mc.addItem ("Choice number #1");
		mc.addItem ("Choice number #2");
		mc.addItem ("Choice number #3");
		
		tfx = new TextField (30);

		bb = new browseButton ( );
		bb.setFrame (myWiz);

		myWiz.setPages( 3 );
		
		
		myWiz.addItem ( 1, new Label ());
		myWiz.addItem ( 1, new Label ("Select an option :                   "));
		myWiz.addItem ( 1, mc);
		
		myWiz.addItem ( 2, new Label ());
		myWiz.addItem ( 2, bb);
		myWiz.addItem ( 2, new Label ());
		myWiz.addItem ( 2, new Label ("Write something                   "));
		myWiz.addItem ( 2, tfx);

		myWiz.addItem ( 3, new Label ());
		myWiz.addItem ( 3, new Label ("Click Finish to display the results"));
		myWiz.setFinish (  2 , true);
		myWiz.addPicture ("demo.gif");
		myWiz.show();
	}
	
	
	
	
	// APPLET INFO SUPPORT:
	//		The getAppletInfo() method returns a string describing the applet's
	// author, copyright date, or miscellaneous information.
    //--------------------------------------------------------------------------
	public String getAppletInfo()
	{
		return "Name: WizardExec\r\n" +
		       "Author: Panos\r\n" +
		       "e-mail: teras@writeme.com";
	}


}