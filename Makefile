# Remember to check the Makefile in the src directory


all:
	( cd ./src; make all )

clean:
	rm -f *~ core
	( cd ./src; make clean )

run:
	( cd ./src; make run )

install:
	( cd ./src; make install)

