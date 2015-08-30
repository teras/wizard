package panos.awt;

import java.awt.Frame;
import java.awt.Button;
import java.awt.Panel;
import java.awt.Dialog;
import java.awt.Label;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class Msgbox extends Dialog
{
	Button ok;

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

		setSize (220, 150);

		// Add the event handling routine for Java 1.1
		ok.addActionListener (new ActionListener ()
		{ public void actionPerformed (ActionEvent evt)
			{ clickedOK (evt); }
		} );
		setVisible (true);

	}

	public void clickedOK (ActionEvent evt)
	{
		dispose();
	}
}
