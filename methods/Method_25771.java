private boolean isOwnedBy(Symbol sym,Symbol owner,Types types){
  if (sym.owner == owner) {
    return true;
  }
  if (owner instanceof TypeSymbol) {
    return sym.isMemberOf((TypeSymbol)owner,types);
  }
  return false;
}
