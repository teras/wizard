package panos.awt;

/**
* This is the interface model, in order for a class to recieve action
* events from the wizard. You have to implement this interface to your
* class, or else you can not use the Wizard.<BR>
* You should implement all of those methods in your class, or your
* class will be abstract.
*/
public interface WizardListener
{
	/** 
	* This method is called when the Finish button is clicked.
	*/
	public void clickedFinish ();

	/** 
	* This method is called when the Cancel button is clicked.
	*/
	public void clickedCancel ();
	
	/**
	* This method is called every time the Wizard changes the current page.
	* @param pg the new page number (first page has pagenumber 1)
	*/	
	public void changedPage( int pg );
	
	/** This method is called when a wizButton is clicked
	*
	* @see panos.awt.wizButton
	* @param wb handler to the wizButton which was clicked.
	*/ 
	public void performedAction ( wizButton wb );
}