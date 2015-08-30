// ******************************************************************************
// WizardExec.java:	Application
//  This application demostrates the use of the Wizard class
// ******************************************************************************
import java.awt.Choice;
import java.awt.TextField;
import java.awt.Label;
import java.awt.Button;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import panos.awt.browseButton;
import panos.awt.Wizard;
import panos.awt.WizardListener;

//==============================================================================
// Main Class for applet WizardExec
//
//==============================================================================
public class WizardExec implements WizardListener
{

	// We need these handlers, so that we can get the data from the wizard back
	TextField tfx;
	browseButton bb;
	Choice mc ;
	Button but2;
	
	Wizard myWiz;

	// constructor if this class
	public WizardExec(){}


 	// Application starts here
	public static void main(String argv[])  
	{ 
		WizardExec me = new WizardExec(); 
		me.createWizard(); 
		me.myWiz.displayWizard();  
	} 
 

	// routine to handle the exit of a wizard
	public void exitWizard(boolean finish)
	{
		if (finish)
		{
			System.out.println (mc.getSelectedItem() + " - " + tfx.getText() + " - " +bb.getFilename ());
		}
		else
		{
			System.out.println ("Cancel clicked"); 
		}
		System.exit(0); 
	}

	// routine to handle advance page action
	public int nextPage ( int pg )
	{
		if (pg == 1)
		{
			if ( mc.getSelectedIndex() == 0 ) return 2;
			if ( mc.getSelectedIndex() == 1 ) return 3;
		}
		return 0;
	}



	// Create the user interface of the wizard
	public void createWizard()
	{

		myWiz = new Wizard(this);
		
		mc= new Choice();
		mc.addItem ("Jump to page #2.");
		mc.addItem ("Jump to page #3.");
		mc.addItem ("Go normal.");
		
		tfx = new TextField (30);

		bb = new browseButton ( );
		bb.setFrame (myWiz);

		but2 = new Button ("Default action button");

		
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
		myWiz.addItem (3, but2 );

		myWiz.setFinish (  2 , true);
	//	myWiz.addPicture ("images/demo1.gif");	// use either of these methods
		myWiz.addPicture (1, "images/demo1.gif");
		myWiz.addPicture (2, "images/demo2.gif");
		myWiz.addPicture (3, "images/demo3.gif");


		String help1 [] = { "Select which will be the next page", "of this wizard or if it", "will continue in normal way" };
		String help2 [] = { "This is help for", "the second page"};
		myWiz.setHelpText (1, help1);
		myWiz.setHelpText (2, help2);

		myWiz.setTitle ("Wizard demo V 0.9.4");
		
		but2.addActionListener (new ActionListener ()
		{ public void actionPerformed (ActionEvent evt)
			{
				clickedButton ();
			}
		} );
	}

	public void clickedButton ()
	{
		System.out.println ("Button clicked!");
	}
}
