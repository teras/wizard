package panos.awt;

import java.awt.Frame;
import java.awt.TextField;
import java.awt.FileDialog;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class browseButton extends Button
{
	
	public TextField bTextField;
	public Frame bFrame;
	private FileDialog FDialog;

	// Constructors of this class
	public browseButton ()
	{
		this ( "Browse...");
	}
	public browseButton ( TextField tf )
	{
		this ( "Browse..." , tf );
	}
	public browseButton ( String str )
	{
		this ( str , new TextField (25) );
	}
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
	public void callBrowser ( ActionEvent ev )
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


	// a method to get the handler for the TextField
	public TextField getTextField ()
	{
		return bTextField;
	}
	
	// a method to get the handler for the TextField
	public String getFilename ()
	{
		return bTextField.getText();
	}
	
	// with this method you can assign a frame to a button
	// in order for the fille dialog to be modal
	public void setFrame (Frame fr)
	{
		bFrame = fr;
	}
}
