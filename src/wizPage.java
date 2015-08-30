package panos.awt;

import java.awt.*;
import panos.awt.WizardLayout;

// wizPage classs
// use the methods in this class to set the wizard pages

public class wizPage extends Panel
{

	public String HelpText[]; // somewhere to store the help text of this page
	public boolean canFinish; // true if the "finish" button can be clicked in this page
	private Frame f_Help; // Frame to display when clicking on Help button

	public wizPage ()
	{
		setBackground(Color.lightGray);
		setForeground(Color.black);
		canFinish = false;
		setLayout (new WizardLayout ());
	}
	
	// add an object to the current page
	public void addItem ( Object obj )
	{
		if (obj instanceof List) add((List)obj);
		if (obj instanceof Label) add((Label)obj);
		if (obj instanceof TextField) add((TextField)obj);
		if (obj instanceof Canvas) add((Canvas)obj);
		if (obj instanceof TextArea) add ((TextArea)obj);
		if (obj instanceof Checkbox) add ((Checkbox)obj);
		if (obj instanceof Choice) add ((Choice)obj);
		if (obj instanceof wizButton) add ((wizButton)obj);
		if (obj instanceof browseButton)
		{
			 browseButton bb = (browseButton)obj;
			 add (bb);
			 add ( bb.getTextField());
		}
	}
		
}
