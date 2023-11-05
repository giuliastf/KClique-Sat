default: build

build:
	javac Main.java
	javac Graph.java
	cp main.sh main && chmod +x main

clean:
	rm -rf main *.class