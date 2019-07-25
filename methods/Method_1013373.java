public final boolean contains(TBPar par){
  for (int i=0; i < this.size(); i++) {
    if (par.equals(this.parAt(i))) {
      return true;
    }
  }
  return false;
}
