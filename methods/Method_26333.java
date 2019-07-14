private Symbol getSuperType(Symbol symbol,String name){
  for (  Type t : types.closure(symbol.type)) {
    if (t.asElement().getSimpleName().contentEquals(name)) {
      return t.asElement();
    }
  }
  return null;
}
