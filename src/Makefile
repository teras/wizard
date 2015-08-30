JVM=java
JCOMPILER=javac
JDOC=javadoc
JC_OPTIONS=-d .


#
# User interact stops here
#


JC=${JCOMPILER} ${JC_OPTIONS}



all:
	@echo If something goes wrong, try to execute \'make stepwise\' instead of \'make all\'
	${JC} *.java
	@echo --- Operation successfull ---
	@echo You can now execute a demo wizard with this command:  \'java WizardExec\' from the src directory, or type now \'make run\' at the command line.

stepwise: panos/awt/Line3D.class panos/awt/WizardListener.class panos/awt/WizardLayout.class panos/awt/Msgbox.class panos/awt/wizImage.class panos/awt/browseButton.class panos/awt/wizPage.class panos/awt/Wizard.class WizardExec.class
	@echo Compilation successfull

clean:
	rm -f -r panos
	rm -f *.class core *~ .directory *.html *.mtf *.jar
	rm -f -r ../docs
	rm -f ../*~
	
install:
	#################################################
	#     copy the *.class files wherever you want  #
	#################################################

demo:
	${JVM} WizardExec
	
docs:
	rm -f -r ../docs
	mkdir ../docs
	mkdir ../docs/images
	@echo Copying images...
	cp  docfiles/*.gif ../docs/images
	@echo Copying HTML documents...
	cp  docfiles/*.html ../docs
	${JDOC} -d ../docs Line3D.java WizardListener.java Wizard.java  browseButton.java Msgbox.java WizardLayout.java wizImage.java wizPage.java

panos/awt/WizardLayout.class: WizardLayout.java
	${JC} WizardLayout.java
	
panos/awt/wizImage.class: wizImage.java
	${JC} wizImage.java

panos/awt/Line3D.class:
	${JC} Line3D.java

panos/awt/Wizard.class:
	${JC} Wizard.java

panos/awt/browseButton.class: browseButton.java
	${JC} browseButton.java

panos/awt/Msgbox.class: Msgbox.java
	${JC} Msgbox.java

panos/awt/wizPage.class: wizPage.java
	${JC} wizPage.java

panos/awt/WizardListener.class: WizardListener.java
	${JC} WizardListener.java

WizardExec.class: WizardExec.java
	${JC} WizardExec.java

