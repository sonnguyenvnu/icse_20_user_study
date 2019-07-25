public final TLCState unbind(UniqueString name){
  int loc=name.getVarLoc();
  this.values[loc]=null;
  return this;
}
