package panos.awt;

import java.awt.*;
import panos.awt.WizardLayout;

/**
* Wizard class uses this class in order to display the various wizard pages.
* Every wizard page is another instance of this class. Usually you
* don't have to use this class and it's methods directly, Wizard class
* does it for you.
*
* @see panos.awt.Wizard#getWizPage
*/
public class wizPage extends Panel
{

	/**
	* The help thext of this wizard page. <I>Still experimental.<I>
	*/
	public String HelpText[]; // somewhere to store the help text of this page
	
	// somewhere to store the previous page, useful only with multi-paged wizards.
	// defined as "friendly"
	int prevPage;

	/**
	* True if wizard can end at this point, false if not.
	* @see panos.awt.Wizard#setFinish
	*/
	public boolean canFinish; // true if the "finish" button can be clicked in this page

	private Frame f_Help; // Frame to display when clicking on Help button
	private Panel pOptions; // Panel where all the selectable items are stored
	private wizImage Img; // panel where the pictures are stored

	/**
	* Create a new wizard page.
	* @param prPage which is the previous page. Useful only with interactive
	* multi-paged wizards. Usually set just to previous page.
	*/
	public wizPage (int prPage)
	{
		pOptions = new Panel();
		
		setLayout( new BorderLayout());
		pOptions.setLayout( new WizardLayout()); // the LayoutManager of the wizard page
		
		add( "Center", pOptions);
		canFinish = false;
	}
	
	/**
	* Add an object to the current page.
	* @see panos.awt.Wizard#addItem
	*/
	public void addItem ( Object obj )
	{
		if (obj instanceof List) pOptions.add((List)obj);
		if (obj instanceof Label) pOptions.add((Label)obj);
		if (obj instanceof TextField) pOptions.add((TextField)obj);
		if (obj instanceof Canvas) pOptions.add((Canvas)obj);
		if (obj instanceof TextArea) pOptions.add((TextArea)obj);
		if (obj instanceof Checkbox) pOptions.add((Checkbox)obj);
		if (obj instanceof Choice) pOptions.add((Choice)obj);
		if (obj instanceof Button) pOptions.add((Button)obj);
		if (obj instanceof browseButton)
		{
			 browseButton bb = (browseButton)obj;
			 pOptions.add (bb);
			 pOptions.add ( bb.getTextField());
		}
	}
	
	
	/**
	* Add a picture to your wizard page
	*@see panos.awt.Wizard#addPicture
	*/
	public void addPicture (String s_pic, Frame wiz, Object caller)
	{
		Img = new wizImage (s_pic, wiz, caller);
		add ("West", Img);		
	}
	
	/**
	* Get a handler for the wizard page handler.
	* @see panos.awt.Wizard#getWizPage
	*/
	public Panel getPanel()
	{
		return pOptions;
	}
	
}
