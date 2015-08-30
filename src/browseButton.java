package panos.awt;

import java.awt.*;

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
	}

	
	// Handle the events of this component
	public boolean action (Event ev, Object obj)
	{
		String fname;
		
		if ( ev.target  == this )
		{
			if (bFrame == null)
				bFrame = new Frame();
			if (FDialog == null)
				FDialog = new FileDialog (bFrame, "Please select a file name.");
			FDialog.show();
			fname = FDialog.getFile();
			if ( fname != null)
				bTextField.setText ( FDialog.getDirectory() + fname);
			return true;
		}
		return false;
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