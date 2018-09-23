JFLAGS = -g
JC = javac
.SUFFIXES: .java .class
.java.class:
		$(JC) $(JFLAGS) $*.java

CLASSES = \
		Iperfer.java \
		IperfClient.java \
		IperfServer.java

default: classes

classes: $(CLASSES:.java=.class)

clean:
		$(RM) *.class