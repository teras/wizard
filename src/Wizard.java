package panos.awt;

import java.awt.Button;
import java.awt.Panel;
import java.awt.Frame;
import java.awt.Color;
import java.awt.CardLayout;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Canvas;
import java.awt.SystemColor;
import java.awt.Label;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.util.Vector;

import panos.awt.WizardListener;
import panos.awt.Line3D;

/**
* This is the main class of the Wizard component. A normal developer
* should use ONLY the methods provided by this class and communicate
* ONLY with this class. All other classes are helper-classes and they
* shouldn't be used.<BR>
*
* @author Panos Katsaloulis
* @version 0.9.2
*/
public class Wizard extends Frame
{
	
	/**
	* The currently displaying wizard page. First page has number <B>1</B> not 0.
	*/
	public int currentPage ; // which is the current page
	
	private Vector pages; //somewhere to store the pages of this wizard
	private Panel p_Card ; // panel for the wizard pages
	private CardLayout cards ; // layout for the wizard pages
	private Button b_Begin ; // buttin to go to the first card
	private Button b_Prev ;  // button to go to the previous card
	private Button b_Next ; // button to go to the next card
	private Button b_Finish ; // button to finish the wizard
	private Button b_Cancel ; // button to cancel the wizard
	private Button b_Help ; // button to display a help frame
	
	private WizardListener caller ;	// pointer to store the current object, which called
								//  this wizard. This pointer is used in order to
								// inform this object of any wizard events.

	/**
	* Create a new Wizard with default size
	*
	* @param apl the component which recieves the Wizard events.
	* Usually it is the caller class. If you intent to use the Wizard
	* in a browser, you <I>must</I> provide the applet class as a parameter,
	* or else the Wizard couldn't display any pictures.
	*/
	public Wizard (WizardListener apl) // default constructor
	{
		this(apl, 500, 300 );
	}
	
	
	/**
	* Create a new Wizard with specified size
	*
	* @param apl the component which recieves the Wizard events.
	* Usually it is the caller class. If you intent to use the Wizard
	* in a browser, you <I>must</I> provide the applet class as a parameter,
	* or else the Wizard couldn't display any pictures.
	* @param xLen the width of the Wizard
	* @param yLen the height of the Wizard
	*/
	public Wizard (WizardListener wl, int xLen, int yLen)
	{
		pages = new Vector(0,1);  // only one start page and then increasement +1
		
		// define the Panel for the Wizard Pages
		// It MUST be defined here and not int create first, 
		// because addPage() needs it ...
		cards = new CardLayout(); // the Layout for the wizard pages
		p_Card = new Panel();
		p_Card.setLayout (cards);
		
		addPage (); // Add the first page
		createFirstCard (xLen, yLen); 	// this is the first page to be added => create layout etc.
		caller = wl; // set the parent caller, in order to send an event there
		setActionListeners();
	}


	// Here we try to add the various action listeners for Java 1.1
	private void setActionListeners ()
	{

		b_Begin.addActionListener (new ActionListener ()
		{ public void actionPerformed (ActionEvent evt)
			{ clickedBegin (evt); }
		} );
 
		b_Prev.addActionListener (new ActionListener ()
		{ public void actionPerformed (ActionEvent evt)
			{ clickedPrev (evt); }
		} );
 
		b_Next.addActionListener (new ActionListener ()
		{ public void actionPerformed (ActionEvent evt)
			{ clickedNext (evt); }
		} );
 
		b_Finish.addActionListener (new ActionListener ()
		{ public void actionPerformed (ActionEvent evt)
			{ clickedFinish (evt); }
		} );
 
		b_Cancel.addActionListener (new ActionListener ()
		{ public void actionPerformed (ActionEvent evt)
			{ clickedCancel (evt); }
		} );
 
		b_Help.addActionListener (new ActionListener ()
		{ public void actionPerformed (ActionEvent evt)
			{ clickedHelp (evt); }
		} );
	}


	// Now begin the various handling routines for every button
	
	private void clickedBegin (ActionEvent ev)
	{
		cards.first(p_Card); 
		 currentPage = 1; 
		setButtons ( currentPage ); 
		caller.changedPage( currentPage ); 
	}

	private void clickedPrev (ActionEvent ev)
	{
		cards.previous(p_Card); 
		currentPage --; 
		setButtons ( currentPage ); 
		caller.changedPage( currentPage ); 
	}

	private void clickedNext (ActionEvent ev)
	{
		cards.next(p_Card);
		currentPage ++; 
		setButtons ( currentPage );
		caller.changedPage( currentPage ); 
	}

	private void clickedFinish (ActionEvent ev)
	{
		setVisible(false);
		dispose(); 
		caller.clickedFinish();
	}

	private void clickedCancel (ActionEvent ev)
	{
		setVisible(false); 
		dispose(); 
		caller.clickedCancel(); 
	}

	private void clickedHelp (ActionEvent ev)
	{
		wizPage wp;
		String title = "Help for wizard page #" + currentPage;
		wp = (wizPage)pages.elementAt (currentPage-1);
		Msgbox msg = new Msgbox (this, title , wp.HelpText );
	}


	// set the condition of the navigation buttons
	private void setButtons ( int page )
	{
		if ( ! existsPage (page) ) return;	// exit if this page doesn't exist
		if ( page < pages() )	b_Next.setEnabled(true);	else	b_Next.setEnabled(false);
		if ( page > 1)			b_Prev.setEnabled(true);	else	b_Prev.setEnabled(false);
		if ( page > 1)			b_Begin.setEnabled(true); 	else	b_Begin.setEnabled(false);
		wizPage wp = (wizPage)pages.elementAt(page-1);
		if ( wp.canFinish )		b_Finish.setEnabled(true);	else	b_Finish.setEnabled(false);
	}

	
	/**
	* With this method you can set the finish button to any page.
	* 
	* @param page the page number you want to change the finish button state
	* @param finish state of the finish button. If <I>true</I> the
	* finish button is anabled and you exit the Wizard at this page. If
	* <I>false</I> the finish button is desabled and you can not exit
	* the Wizard at this point.
	* <BR><BR>
	* By default the finish button is disabled for all the wizard pages
	* except the last one. Sometimes there is a need to finish the
	* wizard earlier. With this method you can set manually which pages
	* you can exit with the finish button.<BR>
	* Remember that in the last page the finish button is <B>always</B> enabled.
	*/
	public void setFinish ( int page, boolean finish)
	{
		wizPage wp;
		if ( !existsPage (page) ) return; // exit if this page don't exists
		wp = (wizPage)pages.elementAt(page-1);
		wp.canFinish = finish;
	}		

	/**
	* With this method you can set a little help text for every Wizard
	* page. This feature is still experimental and it is possible to
	* change dramatically in the future. 	
	* @param page the page number to display the supplied text
	* @param txt[] the help text in a String array. Every line of text is an array element
	*/
	public void setHelpText ( int page, String txt[])
	{
		wizPage wp;
		if ( ! existsPage (page) ) return; // exit if this page don't exists
		wp = (wizPage)pages.elementAt (page-1);
		wp.HelpText = txt;
	}

	/**
	* With this method you can set the number of pages for this Wizard.<BR>
	* 
	* @param hm_pages the absolute number of pages. <BR>
	* That means if you call mywiz.setPages (5), in your program just
	* after the constructor, you create 5 pages and not 6, as opposed
	* to the addPage method. <BR>
	* If you use it for a second time with a greater number of pages as
	* before, you can add pages with this method. It is a quicker method
	* than addPage().<BR>
	* Remember that by creating a Wizard, you create it's first page too.
	* So if you use the addPage() method, after you created a wizard, you
	* will have <B>2</B> pages instead of 1.
	*
	* @see #addPage
	*/
	public void setPages (int hm_pages)
	{
		if (hm_pages <= 0 || hm_pages > 100 ) return ;// check that number of pages is between the limits
		if ( hm_pages > pages() )	// check that we ADDING pages. We cannot delete pages
		{									// with this command
			int n=hm_pages - pages();
			for ( int f = 1 ; f <= n ; f++) // for every page we want to add ...
			{
				this.addPage();
			}
		}
	}
	
		
	/**
	* With this method you can add another page to the Wizard.
	* 
	* @see #setPages
	*/
	public void addPage ()
	{
		pages.addElement (new wizPage ()); // page at end
		int pos = pages();
		p_Card.add( "WizPage" + pos , (Panel)pages.elementAt (pos-1) ); // store the card data for this page
	}


	/**
	* With this method you can get back the number of pages this
	* wizard has.
	* @return the number of pages
	*/
	public int pages ()
	{
		return pages.size();		
	}

	
	/**
	* With this method you can check if the given page number exists.
	*
	* @param pag the page number we want to check if it extsts
	* @return true if exists, false if it doesn't
	*/
	public boolean existsPage ( int pag )
	{
		return ( pages() >= pag && pag>0 );
	}
	

	/** This method returns a handler for a wizard page. It is not recommended though to use this method.
	*
	* @param the page number you hand to get a handler
	* @return the wizard Panel. On ths Panel usually the Wizard adds the
	* various components. You can use this handler to change the LaoutManager
	* for example, or add components not supported by the Wizard.
	* @since changed on 0.9.2
	*/
	public Panel getWizPage (int page)
	{
		wizPage wp;
		if ( existsPage (page) == false ) return null ; // exit if this page don't exists
		else
		{
			wp = (wizPage)pages.elementAt(page-1);
			return wp.getPanel();
		}
	}
	


	// This method is called when we create a card for first time 
	// in order to manupulate the layout of the navigation buttons etc.
	private void createFirstCard(int xLen, int yLen) // you are not supposed to have access to this method
	{
		Panel p_Nav ; // panel for the navigation buttons
		Panel p_lower ; // panel for the wizard bottom

		setLayout ( new BorderLayout());

		setBackground(SystemColor.control);

		// set Navigation Panel
		p_lower = new Panel ();
		p_Nav = new Panel ();
		
		p_lower.setLayout( new BorderLayout ());
		p_lower.add( p_Nav, "Center");
		p_lower.add( new Line3D(), "North");
		p_Nav.setLayout ( new FlowLayout());

		// set Navigation buttons
		b_Begin = new Button (" << Begin ");
		b_Prev = new Button (" < Previous ");
		b_Next = new Button (" > Next ");
		b_Finish = new Button (" Finish ");
		b_Cancel = new Button (" Cancel ");
		b_Help = new Button (" Help ");

		// add navigation buttons
		p_Nav.add (b_Begin);
		p_Nav.add ( new Label (""));
		p_Nav.add (b_Prev);
		p_Nav.add (b_Next);
		p_Nav.add ( new Label ("  "));
		p_Nav.add (b_Finish);
		p_Nav.add ( new Label (""));
		p_Nav.add (b_Cancel);
		p_Nav.add ( new Label ("  "));
		p_Nav.add (b_Help);
		
		Canvas cv = new Canvas ();
		cv.setSize (15,1);

		add ( "Center", p_Card);
		add ( "South", p_lower );
		add ( "East" , cv );
		setResizable (false) ;
		setSize(xLen, yLen);
		currentPage = 1;
	}
	
	
	
	/**
	* You can attach an object to a specific page.
	*
	* @param pag the page number you want to add an object
	* @param obj the pbject you want to add to this page. It should be one of the following formats:<BR>
	* <UL><LI>browseButton</LI>
	* <LI>wizButton</LI>
	* <LI>Canvas</LI>
	* <LI>Checkbox</LI>
	* <LI>Choice</LI>
	* <LI>Label</LI>
	* <LI>List</LI>
	* <LI>TextArea</LI>
	* <LI>TextField</LI></UL>
	*/
	public void addItem ( int pag, Object obj )
	{
		wizPage cpage ;
		if ( existsPage (pag) == false ) return; // exit if this page don't exists
		cpage = (wizPage)pages.elementAt(pag-1); // first page is 0, so we must substract
		cpage.addItem(obj);
	}
	

	/**
	* With this method you can add a picture to a specific page on
	* the left of the wizard.
	*
	* @see #addPicture(java.lang.String)
	* @param pag the page you want to add the picture. All other pages are not affected.
	* @param s_pic the filename of the page. You can use relative or absolute path.
	* <BR><BR><B>Warning!</B><BR>
	* Remember that by adding an image to page already having an image,
	* doesn't destroy the old image but only overlaps it.
	*/
	public void addPicture ( int pag, String s_pic )
	{
		wizPage cpage ;
		if ( ! existsPage (pag))
			return; // exit if this page don't exists
		cpage = (wizPage)pages.elementAt(pag-1); // first page is 0, so we must substract
		cpage.addPicture(s_pic, this, caller);//support with information such as the frame to be redrawn & the (possible) caller applet
	}

	/**
	* With this method you can add the same picture to every page on
	* the left of the wizard.
	*
	* @see #addPicture(int,java.lang.String)
	* @param s_pic the filename of the page. You can use relative or absolute path.
	* <BR><BR><B>Warning!</B><BR>
	* Remember that by adding an image to page already having an image,
	* doesn't destroy the old image but only overlaps it.
	*/
	public void addPicture (String s_pic )
	{
		for ( int f = 1; f <= pages(); f++)
		{
			addPicture (f, s_pic);
		}
	}

	/**
	* Use this method in order to display the wizard.<BR>
	* It is obligatory to use this method instead of <CODE>show()</CODE>.
	*/
	public void displayWizard ()
	{
		wizPage wp ;
		super.setVisible(true);

		wp = (wizPage)pages.lastElement (); // get last page
		wp.canFinish = true ; // last page can be "Finish"-ed

		cards.first(p_Card);
		currentPage = 1;
		setButtons ( currentPage );
	}

}