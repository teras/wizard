package panos.awt;

import java.awt.Button;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import panos.awt.WizardListener;

/**
* This is a class in order to create active action buttons in your
* Wizard. Although you can always add buttons in your wizard, you can
* never have an action handler for them. With this component you can
* solve this problem. You can simply add these buttons to the wizard
* and grab the actions from your caller application. To realize this
* you have to override the method performedAction( wizButton wb) from
* the WizardListener interface.<BR><BR>
* I think, now with the new AWT in Java 1.1 this is not needed anymore.
* Still, I have implemented this and if you want to use it, use it! If
* not, you don't have to. But you HAVE to override the performedAction method of WizardListener.
*
* @see panos.awt.WizardListener
*/
public class wizButton extends Button
{
	
	private WizardListener wl;

	/**
	* Create a new wizButton with specified text
	*
	* @see panos.awt.WizardListener#performedAction
	* @param wl the class which will implement the <CODE>performedAction</CODE> method.
	* @param label_text the caption of this button
	*/
	public wizButton ( WizardListener wl, String label_text ) 
	{ 
		this ( wl );
		this.setLabel  (label_text);
	} 

	/**
	* Create a new wizButton with no caption text.
	*
	* @see panos.awt.WizardListener#performedAction
	* @param wl the class which will implement the <CODE>performedAction</CODE> method.
	*/
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