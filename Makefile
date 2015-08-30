# Remember to check the Makefile in the src directory


all:
	( cd ./src; make all )

stepwise:
	( cd ./src; make stepwise )

clean:
	rm -f *~ core .directory docs/.directory
	( cd ./src; make clean )

run:
	( cd ./src; make run )

install:
	( cd ./src; make install)

