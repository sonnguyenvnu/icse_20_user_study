public final TLCState bind(UniqueString name,IValue value){
  int loc=name.getVarLoc();
  this.values[loc]=value;
  return this;
}
