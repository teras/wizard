

JVM=java
JCOMPILER=javac
JC_OPTIONS=-d .


# User interact stops here


JC=${JCOMPILER} ${JC_OPTIONS}



all:
	@echo If something goes wrong, try to execute \'make stepwise\' instead of \'make all\'
	${JC} *.java
	@echo \*\*\* Operation successfull \*\*\*
	@echo You can now execute a demo wizard with this command:  \'java WizardExec\' from the src directory, or type now \'make run\' at the command line.

stepwise: WizardListener+wizButton WizardLayout Msgbox wizImage browseButton wizPage Wizard WizardExec
	@echo Compilation successfull

clean:
	rm -f -r panos
	rm -f *.class core *~ .directory

install:
	#################################################
	#     copy the *.class files wherever you want  #
	#################################################

run:
	${JVM} WizardExec
	
WizardLayout: WizardLayout.java
	${JC} WizardLayout.java
	
wizImage: wizImage.java
	${JC} wizImage.java

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

