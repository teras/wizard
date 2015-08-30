package panos.awt;
import java.awt.*;
import java.applet.Applet;
import panos.system.utils;
import java.net.URL;

// Use this component in order to add a picture to your wizard

public class wizImage extends Canvas

{
	Image img;
	Frame parent;
	boolean imageLoaded = false;
	
	// constructor
	wizImage (String s_img, Frame par, Applet apl)
	{
		// we don't know if this is an application or an applet
		// so we load the image with both ways - and we
		// utilize an exception for that
		 try 
		{
			img = Toolkit.getDefaultToolkit().getImage (s_img);
		}
		catch (Exception q)
		{
			img = apl.getImage (apl.getDocumentBase (), s_img);
		}

		this.resize (1,1);
		this.setBackground(Color.lightGray);
		parent = par;
	}
	
	// draw the specified image
	public void paint (Graphics g)
	{
		int iw, ih;

		iw = img.getWidth(this); // get dimensions of picture
		ih = img.getHeight(this); //   -//-
		g.drawImage (img, 10, 10, this); // draw image again!

		if (iw!=-1 && !imageLoaded)  // if image is loaded enter this routine (only ONCE!)
		{
			imageLoaded = true; 		// don't enter this routine again
			this.resize (iw + 20,ih +20);		// resize canvas to the proper size
			parent.validate();		// inform paren frame for this resizing
		}
	}
	
}