public final IValue lookup(UniqueString var){
  int loc=var.getVarLoc();
  if (loc < 0)   return null;
  return this.values[loc];
}
