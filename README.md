# wizard

Easily create a wizard, as in in a very popular Operating Systems.

With this component you can easily create a wizard, much like the wizards
used in a very popular O$.

If you are not  Java developer, then you shouldn't be so interested with
this project. You can not use it 'as is'. You have to write first a little
application (or applet) if you want to use it. It serves only 'library'
purposes.

You are free to use it in your programs anyway you like, as long as you
write my name to the credits part. Furthermore, you should read the licence
agreement (file docs/lgpl.html - see file COPYING), which is under the GNU
GPL.
 


# Features

This component serves as an interface between you and a wizard-like frame.
It means that you can create a wizard with the least effort from you, which
will be functional and as close as possible to the wizard used in a
"commercial Operating $ystem".

The component itself takes care of creating the interface, applying the
buttons, move between the pages, displaying a picture and informing your
application about the current state of the wizard. Some of these features are:
 - automatically creates a frame with all the neccesary information and
   components, like the buttons to navigate through the pages, the layout
   manager, the position of all the components etc. The programmer needs only
   to inform the wizard of the inserted components and everything else is done
   automatically.
 - handles the events of the various buttons, so that the programmer doesn't
   need to manually change pages in the wizard.
 - displays a picture on the left of the wizard window. The only thing the
   programmer has to do is give the filename of the picture to be loaded.
 - the programmer can easily define the number of pages of a wizard and if
   the user can exit normally from this page.
 - the programmer is informed whenever a change of the current wizard page is
   done.
 - ability to display a little help message for every wizard page. Still 
   experimental.
 - it supports both applets and applications, with the same compiled / source
   code.
 - it includes a new component: the browseButton. This is a button attached to
   a textfield. If you click on the button a file dilog appears. The filename
   chosen appears in the textfield.
 - Interface driven event - with WizardListener. Not anymore event driven.  
 - Java 1.1 compatible. Dropped the deprecated functions. Sorry Java 1.0
   developers! (If you still think that you need Java 1.0 compatibility
   search for version 0.8.x)
 - No need for the parent class to be only an applet. Now it can be whatever
   you like.
 - Every page can have it's own picture, or all pages can have the same   
   picture.
 - Interactive Wizard is now possible! Not needed anymore to be a serial 
   wizard. You can define various data-paths for your wizard. You can jump   
   from one page to another.
   


# Documentation

You can find some info in the docs directory.
 In order to create this directory you have to enter 'src' and on the command prompt type 'make docs'.

The docs directory is created on the same directory as this README file. The
directory 'docfiles' under the 'src' directory DOES NOT contain any useful
info (Btw thanks to GIMP for the construction of the gif files!).


# Installation
------------

  Information for the installation is in the file 'INSTALL'.


Notes
-----

  The Wizard can't be closed with the "close" button of the window manager (or
the "X" button in win95). It SHOULD me closed either with "Cancel" or
"Finish".

     
# **WARNING**

This version is not compatible with older versions of this component.
Changes have been made to WizardListener interface. Also the wizButton class
has been removed from the package. The Java 1.1 supports a new event handling,
which makes the need of such a component not useful. Sorry for the
inconvenience, but I think that this had to be done. Although the same event
module supports event handling of closed-frames, the old traping event of
wizard listener is kept.


Thank you for downloading this file     :-)

Happy programming!

