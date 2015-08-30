package panos.awt;

import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import panos.awt.WizardListener;

public class wizButton extends Button
{
	
	private WizardListener wl;

	// Constructors of this class
	public wizButton ( WizardListener wl, String label_text ) 
	{ 
		this ( wl );
		this.setLabel  (label_text);
	} 
	public wizButton ( WizardListener wizl )
	{
		super ();

		wl = wizl;

		// Add the event handling routine for Java 1.1
		this.addActionListener (new ActionListener ()
		{ public void actionPerformed (ActionEvent evt)
			{ callAction (evt); }
		} );
	}

	
	// The button is clicked, so we have to display the dialog
	private void callAction ( ActionEvent ev )
	{
		wl.performedAction ( this );
	}

}
