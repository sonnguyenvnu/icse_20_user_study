/** 
 * Returns the  {@link MethodSymbol} of the first method that sym overrides in its supertypeclosure, or  {@code null} if no such method exists.
 */
private MethodSymbol getFirstOverride(Symbol sym,Types types){
  ClassSymbol owner=sym.enclClass();
  if (ignoreInterfaceOverrides && owner.isInterface()) {
    return null;
  }
  for (  Type s : types.closure(owner.type)) {
    if (s == owner.type) {
      continue;
    }
    for (    Symbol m : s.tsym.members().getSymbolsByName(sym.name)) {
      if (!(m instanceof MethodSymbol)) {
        continue;
      }
      MethodSymbol msym=(MethodSymbol)m;
      if (msym.isStatic()) {
        continue;
      }
      if (sym.overrides(msym,owner,types,false)) {
        return msym;
      }
    }
  }
  return null;
}
