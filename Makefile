pdf:
	latexmk -pdf relazione.tex

clean:
	rm -f *.pdf *.toc *.out *.log *.aux *.dvi *.synctex.gz *.auto.dot *.fls *.fdb_latexmk