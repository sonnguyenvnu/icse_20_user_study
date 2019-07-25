public final boolean occur(SymbolNode[] params){
  for (int i=0; i < params.length; i++) {
    if (this == params[i])     return true;
  }
  return false;
}
