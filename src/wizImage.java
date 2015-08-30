package panos.awt;
import java.awt.*;
import java.applet.Applet;
import java.net.URL;
import java.awt.SystemColor;

/**
* Wizard class uses this component in order to add a picture to the left of your wizard.
* Usually you don't need to have access to this class.
*/
class wizImage extends Canvas

{
	Image img;
	Frame parent;
	boolean imageLoaded = false;
	
	/**
	* Create a new wizImage.
	*
	* @param s_img The filename of the picture to display
	* @param Frame the parent Frame of this page (usually the Wizard)
	* @param caller the user caller class, the class which implemented the WizardListener interface. It is the same caller class as with Wizard.
	*/
	wizImage (String s_img, Frame par, Object caller)
	{
		Applet apl;

		// we don't know if this is an application or an applet
		// so we load the image with both ways - and we
		// utilize an exception for that
		try 
		{
			img = Toolkit.getDefaultToolkit().getImage (s_img);
		}
		catch (Exception q)
		{
			apl = (Applet)caller;
			img = apl.getImage ( apl.getDocumentBase (), s_img);
		}

		this.setSize (1,1);
		this.setBackground(SystemColor.control);
		parent = par;
	}
	
	/**
	* Repaints the image
	*@param g the graphics handler
	*/
	public void paint (Graphics g)
	{
		int iw, ih;

		iw = img.getWidth(this); // get dimensions of picture
		ih = img.getHeight(this); //   -//-
		g.drawImage (img, 10, 10, this); // draw image again!

		if (iw!=-1 && !imageLoaded)  // if image is loaded enter this routine (only ONCE!)
		{
			imageLoaded = true; 		// don't enter this routine again
			this.setSize (iw + 20,ih +20);		// resize canvas to the proper size
			parent.validate();
		// inform paren frame for this resizing
 		}
	}
	
}