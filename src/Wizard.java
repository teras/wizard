package panos.awt;

import java.awt.*;
import java.applet.Applet;
import java.util.Vector;


// Wizard classs
// use the methods in this class to invoke a wizard
// needs class wizPage

public class Wizard extends Frame
{
	
	private Vector pages; //somewhere to store the pages of this wizard

	private Panel p_Nav ; // panel for the navigation buttons
	private Panel p_Card ; // panel for the wizard pages
	
	private CardLayout cards ; // layout for the wizard pages
	public int currentPage ; // which is the current page
	
	private Button b_Begin ; // buttin to go to the first card
	private Button b_Prev ;  // button to go to the previous card
	private Button b_Next ; // button to go to the next card
	private Button b_Finish ; // button to finish the wizard
	private Button b_Cancel ; // button to cancel the wizard
	private Button b_Help ; // button to display a help frame
	
	private Applet caller ;	// pointer to store the current applet, which called
								//  this wizard. This pointer is used in order to
								// send an event on wizard exit

	public static final int EventId = 7500 ;	// id of event to sent to the parent applet
							// This variable is public, in order to be able to
							// read this number. I am thinking of a model
							// to check if it collides with other ids os
							// event number. This number is given as result
							// when the wizard exits with "Finish" button.
							// If the wizard exits with "Cancel" buton, the
							// EventId+1 number is given as result
	
	private wizImage wiz_img ; // Image tobe displayed on the left of this wizard

	// Constructors for this class
	public Wizard (Applet apl) // default constructor
	{
		this(apl, 500, 300 );
	}
	
	
	public Wizard (Applet apl, int xLen, int yLen)
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
		caller = apl; // set the parent applet, in order to send an event there
	}



    // Where all the events are handled (look also at action method)
    public boolean handleEvent(Event evt)
    {
		// handleEvent()
		if (evt.id == Event.ACTION_EVENT && evt.target == b_Cancel)
		{
			hide();
			Event ev = new Event (caller, EventId + 1, this);
			dispose();
			caller.handleEvent(ev);
			return true;
		}
		if (evt.id == Event.ACTION_EVENT && evt.target == b_Finish)
		{
			hide();
			Event ev = new Event (caller, EventId, this);
			dispose();
			caller.handleEvent(ev);
			return true;
		}
		else if ( evt.id == Event.ACTION_EVENT && evt.target == b_Next )
		{
			cards.next(p_Card);
			currentPage ++;
			setButtons ( currentPage );
			return true;
		}
		else if ( evt.id == Event.ACTION_EVENT && evt.target == b_Prev )
		{
			cards.previous(p_Card);
			currentPage --;
			setButtons ( currentPage );
			return true;
		}
		else if ( evt.id == Event.ACTION_EVENT && evt.target == b_Begin )
		{
			cards.first(p_Card);
			currentPage = 1;
			setButtons ( currentPage );
			return true;
		}
		return false;
    }


	// set the condition of the navigation buttons
	private void setButtons ( int page )
	{
		if ( ! existsPage (page) ) return;	// exit if this page doesn't exist

		// set Next button	
		if ( page < pages.size() )	b_Next.enable();	else	b_Next.disable();
		// set Previous button
		if ( page > 1)					b_Prev.enable();	else	b_Prev.disable();
		//set Begin button
		if ( page > 1)					b_Begin.enable(); else	b_Begin.disable();
		//set Finish button
		wizPage wp = (wizPage)pages.elementAt(page-1);
		if ( wp.canFinish )			b_Finish.enable();else	b_Finish.disable();
	}

	
	

	// With this method you can set the finish button to every page
	public void setFinish ( int page, boolean finish)
	{
		wizPage wp;
		if ( existsPage (page) == false ) return; // exit if this page don't exists
		wp = (wizPage)pages.elementAt(page-1);
		wp.canFinish = finish;
	}		


	// With this method you can set the number of pages for this Wizard
	// You can add pages with this method, if you want to
	// This method sets the ABSOLUTE number of pages. That means it has
	// nothing to do with creators call to addPage() method
	// So, if you call 'setPages (5) you create _5_ pages and not
	// 6, (as opposed to addPage method
	public void setPages (int hm_pages)
	{
		if (hm_pages <= 0 || hm_pages > 100 ) return ;// check that number of pages is between the limits
		if ( hm_pages > pages.size() )	// check that we ADDING pages. We cannot delete pages
		{									// with this command
			int n=hm_pages - pages.size();
			for ( int f = 1 ; f <= n ; f++) // for every page we want to add ...
			{
				this.addPage();
			}
		}
	}
	
		
	// With this method you can add a page at the end of the current stream
	// of pages
	// REMEMBER!!!! the creator of Wizard object CREATES ALREADY a page
	// so that it will never be an empty Wizard. If you manually call this
	// method, remember that you add the second, third etc. page 
	public void addPage ()
	{
		pages.addElement (new wizPage ()); // page at end
		int pos = pages.size();
		p_Card.add( "WizPage" + pos , (Panel)pages.elementAt (pos-1) ); // store the card data for this page
	}


	// With this method you can get back the number of pages this wizard has
	public int pages ()
	{
		return pages.size();		
	}

	
	//with this method you can check if the given 
	// page number exists
	public boolean existsPage ( int pag )
	{
		return ( pages.size()>=pag && pag>0 );
	}
	

	// This method returns a handler for a wizard page
	// It is not recommended though to use this method
	public wizPage getWizPage (int page)
	{
		if ( existsPage (page) == false ) return null ; // exit if this page don't exists
		else return (wizPage)pages.elementAt(page-1);
	}
	


	// This method is called when we create a card for first time 
	// in order to manupulate the layout of the navigation buttons etc.
	private void createFirstCard(int xLen, int yLen) // you are not supposed to have access to this method
	{
		this.setLayout ( new BorderLayout());

		// set Navigation Panel
		p_Nav = new Panel ();
		p_Nav.setBackground(Color.lightGray);
		p_Nav.setLayout ( new FlowLayout());

		// set Navigation buttons
		b_Begin = new Button (" << Begin "); // buttin to go to the first card
		b_Prev = new Button (" < Previous ");  // button to go to the previous card
		b_Next = new Button (" > Next "); // button to go to the next card
		b_Finish = new Button (" Finish "); // button to finish the wizard
		b_Cancel = new Button (" Cancel "); // button to cancel the wizard
		b_Help = new Button (" Help "); // button to display a help frame

		// add navigation buttons
		p_Nav.add (b_Begin);
		p_Nav.add (b_Prev);
		p_Nav.add (b_Next);
		p_Nav.add (b_Finish);
		p_Nav.add (b_Cancel);
		// p_Nav.add (b_Help);
		
		Canvas cv = new Canvas ();
		cv.setBackground(Color.lightGray);
		cv.resize (15,1);

		add ( "Center", p_Card);
		add ( "South", p_Nav );
		add ( "East" , cv );
		setResizable (false) ;
		resize(xLen, yLen);
		currentPage = 1;
	}
	
	
	
	// With this method you can attach an object to a specified page
	// First create the object and then pass it as parameter to this method
	//
	public void addItem ( int pag, Object obj )
	{
		wizPage cpage ;
		if ( existsPage (pag) == false ) return; // exit if this page don't exists
		cpage = (wizPage)pages.elementAt(pag-1); // first page is 0, so we must substract
		cpage.addItem(obj);
	}
	

	// With this method you can add a picture on the left of this wizard
	public void addPicture ( String s_pic )
	{
		wiz_img = new wizImage (s_pic , this, caller);
		this.add ("West", wiz_img);		
	}
	
	
	public void show ()
	{
		wizPage wp ;
		super.show();

		wp = (wizPage)pages.lastElement (); // get last page
		wp.canFinish = true ; // last page can be "Finish"-ed

		cards.first(p_Card);
		currentPage = 1;
		setButtons ( currentPage );
	}

}