@echo off

set JVM=java
set JCOMPILER=javac
set JC_OPTIONS=-d .

rem *****************************
rem *  User interact stops here *
rem *****************************

set JC=%JCOMPILER% %JC_OPTIONS%


if "%1%" == "" goto all
if "%1%" == "all" goto all
if "%1%" == "stepwise" goto stepwise
if "%1%" == "clean" goto clean
if "%1%" == "demo" goto demo
if "%1%" == "docs" goto docs
goto error



:error
echo Please select all/docs/stepwise/clean/demo
goto end

:all
%JC% *.java
echo If something goes wrong, try to execute "make stepwise" instead of "make all"
echo You can now execute a demo wizard with this command:  "java WizardExec" from the src directory, or type now "make run" at the command line.
goto end


:clean
deltree /Y panos
deltree /Y *.class
deltree /Y *.html
deltree /Y *.htm
deltree /Y ..\docs
deltree /Y *.mft
deltree /Y *.jar
goto end

:docs
deltree /Y ..\docs
mkdir ..\docs
mkdir ..\docs\images
echo Copying images...
copy >nul: docfiles\*.gif ..\docs\images
echo Copying HTML documents...
copy >nul: docfiless\*.html ..\docs
javadoc -d ..\docs Line3D.java WizardListener.java Wizard.java browseButton.java Msgbox.java WizardLayout.java wizButton.java wizImage.java wizPage.java
goto end

:demo
java WizardExec
goto end


:stepwise
%JC% Line3D.java
%JC% WizardListener.java wizButton.java
%JC% WizardLayout.java
%JC% Msgbox.java
%JC% wizImage.java
%JC% browseButton.java
%JC% wizPage.java
%JC% Wizard.java
%JC% WizardExec.java
goto end





:end
