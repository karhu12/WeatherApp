COMPILE=javac
RUN=java
LIBS= -cp /lib/*.jar
SOURCE=App

compile: App.java
	$(COMPILE) $(LIBS) $(SOURCE).java
