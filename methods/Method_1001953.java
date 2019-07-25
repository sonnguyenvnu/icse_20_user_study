final void copy(final SymbolStats source){
  System.arraycopy(source.litLens,0,litLens,0,288);
  System.arraycopy(source.dists,0,dists,0,32);
  System.arraycopy(source.lLiterals,0,lLiterals,0,288);
  System.arraycopy(source.lLengths,0,lLengths,0,259);
  System.arraycopy(source.dSymbols,0,dSymbols,0,32);
}
