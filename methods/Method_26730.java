private UStaticIdent staticMember(Symbol symbol){
  return UStaticIdent.create((ClassSymbol)symbol.getEnclosingElement(),symbol.getSimpleName(),template(symbol.asType()));
}
