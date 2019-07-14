private boolean isLoadedInThisTx(){
  InternalVertex v=getVertex(0);
  return v == v.it();
}
