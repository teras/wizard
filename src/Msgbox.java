package panos.awt;

import java.awt.Frame;
import java.awt.Button;
import java.awt.Panel;
import java.awt.Dialog;
import java.awt.Label;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


/**
* With this class you can display a message dialog.<BR>
* Wizard uses this class ti display little help messages when
* you click on the 'Help' button.<BR>
* <B>Warning!</B> The structure of this class is possible to
* change in the future versions of Wizard. Displaying help texts
* is still experimental.<BR><BR>
* This is an internal class for the Wizard, so please don't use it directly.
*
* @author Panos Katsaloulis
*/
public class Msgbox extends Dialog
{
	/** The 'OK' button of this Dialog
	*/
	Button ok;

	/**
	* Create a new MsgBox.
	*
	* @param parent a handler to the frame, in order to show the MsgBox (usually the Wizard)
	* @param title the window title of the Msgbox
	* @param text[] the String array of the help text to be displayed
	*/
	public Msgbox ( Frame parent , String title, String text[] )
	{
		super (parent, title, true);

		Panel buttons_p = new Panel();
		Panel textarea_p = new Panel ();
		ok = new Button (" O K ");
		add ("South", buttons_p);
		add ("Center", textarea_p);

		buttons_p.add ( ok );

		if  ( text == null)
		{
			textarea_p.add ( new Label ("No Help available"));
		}
		else
		{
			for (int i = 0 ; i < text.length ; i++)
			{
				textarea_p.add ( "Center" , new Label (text[i]));
			}
		}

		pack ();

		// Add the event handling routine for Java 1.1
		ok.addActionListener (new ActionListener ()
		{ public void actionPerformed (ActionEvent evt)
			{ clickedOK (evt); }
		} );
		setVisible (true);

	}

	private void clickedOK (ActionEvent evt)
	{
		dispose();
	}

	
}