

JVM=java
JCOMPILER=javac
JC_OPTIONS=-d .


# User interact stops here


JC=${JCOMPILER} ${JC_OPTIONS}

all: utils WizardLayout wizImage browseButton wizPage Wizard WizardExec
	@echo \*\*\* Operation successfull \*\*\*
	@echo You can now execute a demo wizard with this command:
	@echo \'java WizardExec\' from the src directory
	@echo \(or type now \'make run\' at the command line\)

clean:
	rm -f -r panos
	rm -f *.class core *~

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
	
wizPage: wizPage.java
	${JC} wizPage.java

utils: utils.java
	${JC} utils.java

WizardExec: WizardExec.java
	${JC} WizardExec.java
	
