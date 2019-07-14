private <T extends Symbol>T getMember(Class<T> type,ElementKind kind,Symbol classSymbol,String name){
  if (classSymbol.type == null) {
    return null;
  }
  for (  Type t : types.closure(classSymbol.type)) {
    Scope scope=t.tsym.members();
    for (    Symbol sym : scope.getSymbolsByName(visitorState.getName(name))) {
      if (sym.getKind().equals(kind)) {
        return type.cast(sym);
      }
    }
  }
  if (classSymbol.hasOuterInstance()) {
    T sym=getMember(type,kind,classSymbol.type.getEnclosingType().asElement(),name);
    if (sym != null) {
      return sym;
    }
  }
  if (classSymbol.owner != null && classSymbol != classSymbol.owner && classSymbol.owner instanceof Symbol.ClassSymbol) {
    T sym=getMember(type,kind,classSymbol.owner,name);
    if (sym != null && sym.isStatic()) {
      return sym;
    }
  }
  return null;
}
