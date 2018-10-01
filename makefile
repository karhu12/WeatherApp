COMPILE=javac
RUN=java
LIBS= -cp .:lib/gson-2.8.5.jar
SOURCE=App

compile: App.java
	$(COMPILE) $(LIBS) $(SOURCE).java

compilerun: App.java
	$(COMPILE) $(LIBS) $(SOURCE).java
	$(RUN) $(LIBS) $(SOURCE)