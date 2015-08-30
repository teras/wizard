// ******************************************************************************
// WizardExec.java:	Application
//  This application demostrates the use of the Wizard class
// ******************************************************************************
import java.awt.Choice;
import java.awt.TextField;
import java.awt.Label;
import panos.awt.browseButton;
import panos.awt.Wizard;
import panos.awt.WizardListener;
import panos.awt.wizButton;

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
	wizButton wb1 ;
	
	Wizard myWiz;

	// constructor if this class
	public WizardExec(){}

	public void clickedFinish()
	{
		String tempOut = mc.getSelectedItem() + " - " + tfx.getText() + " - " +bb.getFilename (); 
		System.out.println (tempOut);
		System.exit(0); 
	}

	public void clickedCancel()
	{
		System.out.println ("Cancel clicked");
		System.exit(0); 
	}

	public void changedPage ( int pg )
	{
		System.out.println ("Page changed: page number is " + pg );
	}

	public void performedAction (wizButton wb)
	{
		if ( wb == wb1)
			System.out.println ("Action button 1 clicked !" );
	}



	public static void main(String argv[]) 
	{
		WizardExec me = new WizardExec();
		me.createWizard();
		me.myWiz.displayWizard(); 
	}



	public void createWizard()
	{
		myWiz = new Wizard(this , 500, 500);
		
		mc= new Choice();
		mc.addItem ("Choice number #1");
		mc.addItem ("Choice number #2");
		mc.addItem ("Choice number #3");
		
		tfx = new TextField (30);

		bb = new browseButton ( );
		bb.setFrame (myWiz);

		wb1 = new wizButton (this, "Action button #1");

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
		myWiz.addItem ( 3, wb1 );

		myWiz.setFinish (  2 , true);
		myWiz.addPicture ("demo.gif");


		String help1 [] = { "This is help for 1st page" };
		String help2 [] = { "This is help for", "the second page"};
		myWiz.setHelpText (1, help1);
		myWiz.setHelpText (2, help2);

	}

}
