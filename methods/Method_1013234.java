public final boolean occur(SymbolNode[] symbols){
  for (int i=0; i < symbols.length; i++) {
    if (this.op == symbols[i] || this.param == symbols[i]) {
      return true;
    }
  }
  return false;
}
