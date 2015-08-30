
package panos.awt;

import java.awt.Frame;
import java.awt.TextField;
import java.awt.FileDialog;
import java.awt.Button;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


/**
* With this class you can display a browse button in your
* wizard. <BR>
* A browse button is a button with a TextField attached to it.
* If you click on the button then a file dialog box appears
* and waits for you to select a file name. By clicking in the
* Open button of the file dialog then the contents of theTextField
* are replaced with the directory and file name of the file 
* chosen. <BR>
* Remember that by creating a browseButton class usually you
* create a TextField too, except if you give as parameter an
* already created TextField. Default caption text is "Browse...".
*
* @author Panos Katsaloulis
* @see #setFrame
*/
public class browseButton extends Button
{
	
	/** The handler for the combined Textfield
	*/
	private TextField bTextField;
	/** The handler of the Frame to be modal
	*/
	private Frame bFrame;
	private FileDialog FDialog;

	/**
	* Create a new browseButton with default caption text.
	*/
	public browseButton ()
	{
		this ( "Browse...");
	}
	/**
	* Create a new browseButton with a specified TextField and default caption text
	* @param tf the Textfield to combine with this Button
	*/
	public browseButton ( TextField tf )
	{
		this ( "Browse..." , tf );
	}
	/**
	* Create a new browseButton with specified caption text
	* @param str the caption text of the browseButton
	*/
	public browseButton ( String str )
	{
		this ( str , new TextField (25) );
	}
	/**
	* Create a new browseButton with a specified TextField and caption text
	* @param str the caption text of the browseButton
	* @param tf the Textfield to combine with this Button
	*/
	public browseButton ( String str, TextField tf)
	{
		super ( str );
		bTextField = tf;
		FDialog = null;

		// Add the event handling routine for Java 1.1
		this.addActionListener (new ActionListener ()
		{ public void actionPerformed (ActionEvent evt)
			{ callBrowser (evt); }
		} );

	}

	
	// The button is clicked, so we have to display the dialog
	private void callBrowser ( ActionEvent ev )
	{
		if (bFrame == null)
			bFrame = new Frame();
		if (FDialog == null)
			FDialog = new FileDialog (bFrame, "Please select a file name.");
		FDialog.show();
		String fname = FDialog.getFile();
		if ( fname != null)
			bTextField.setText ( FDialog.getDirectory() + fname);
	}


	/**
	* Get a handler for the combined Textfiled of this browseButton
	*/
	public TextField getTextField ()
	{
		return bTextField;
	}
	
	/**
	* Get the chosen filename (the text in the Textfield) of this browseButton.
	*
	* @return a String with the chosen filename
	*/
	public String getFilename ()
	{
		return bTextField.getText();
	}
	
	/**
	* Assign a frame to the browseButton, in order for the file
	* dialog to be modal.<BR>
	* It is strongly recommended to use this method with the wizard,
	* so that the wizard must wait for the FileDialog to be finished.<BR>
	* 
	* @param  fr the frame which would be modal, when you call
	* the FileDialog. Usually give the wizard as parameter. If
	* you don't execute this method an invisible Frame will be
	* created, in order to display the FileDialog.<BR>
	* Example:<BR>
	* <CODE>BrowseButton brButton.setFrame( myWiz )</CODE>
	*
	*/
	public void setFrame (Frame fr)
	{
		bFrame = fr;
	}
}