package panos.awt;

import java.awt.*;

/**
* This class draws a 2 pixel height 3D line.
* It is used in the wizard to seperate the navigator buttons
* area from the wizard pages.
*/
public class Line3D extends Canvas
{	
	
	/**
	* Creator of a Line3D object
	*/
	public Line3D ()
	{
		super ();
		setSize(getSize().width,3);
	}


	/**
	* Redraws the Line3D object
	*
	* @param g handler to be able to draw this component
	*/	
	public void paint (Graphics g)
	{
		int length = (int)((getSize().width) * 0.9);
		int start = length/8;
		setBackground( SystemColor.control );
		g.setColor (SystemColor.controlShadow);
		g.drawLine (start, 0, length, 0);
		g.setColor (SystemColor.controlLtHighlight);
		g.drawLine (start, 1, length, 1);

	}

}