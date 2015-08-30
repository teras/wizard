JVM=java
JCOMPILER=javac
JDOC=javadoc
JC_OPTIONS=-d .

# User interact stops here

JC=${JCOMPILER} ${JC_OPTIONS}



all:
	@echo If something goes wrong, try to execute \'make stepwise\' instead of \'make all\'
	${JC} *.java
	@echo \*\*\* Operation successfull \*\*\*
	@echo You can now execute a demo wizard with this command:  \'java WizardExec\' from the src directory, or type now \'make run\' at the command line.

stepwise: Line3D WizardListener+wizButton WizardLayout Msgbox wizImage browseButton wizPage Wizard WizardExec
	@echo Compilation successfull

clean:
	rm -f -r panos
	rm -f *.class core *~ .directory *.html *.mtf *.jar
	rm -f -r ../docs

install:
	#################################################
	#     copy the *.class files wherever you want  #
	#################################################

run:
	${JVM} WizardExec
	
docs:
	rm -f -r ../docs
	mkdir ../docs
	mkdir ../docs/images
	@echo Copying images...
	cp  docfiles/*.gif ../docs/images
	@echo Copying HTML documents...
	cp  docfiles/*.html ../docs
	${JDOC} -d ../docs Line3D.java WizardListener.java Wizard.java  browseButton.java Msgbox.java WizardLayout.java wizButton.java wizImage.java wizPage.java

WizardLayout: WizardLayout.java
	${JC} WizardLayout.java
	
wizImage: wizImage.java
	${JC} wizImage.java

Line3D:
	${JC} Line3D.java

Wizard:
	${JC} Wizard.java

browseButton: browseButton.java
	${JC} browseButton.java

Msgbox: Msgbox.java
	${JC} Msgbox.java

wizPage: wizPage.java
	${JC} wizPage.java

WizardListener+wizButton:: WizardListener.java wizButton.java
	${JC} WizardListener.java wizButton.java

WizardExec: WizardExec.java
	${JC} WizardExec.java

