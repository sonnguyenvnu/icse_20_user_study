@SuppressWarnings("TypeEquals") @Nullable public static MethodSymbol findSuperMethodInType(MethodSymbol methodSymbol,Type superType,Types types){
  if (methodSymbol.isStatic() || superType.equals(methodSymbol.owner.type)) {
    return null;
  }
  Scope scope=superType.tsym.members();
  for (  Symbol sym : scope.getSymbolsByName(methodSymbol.name)) {
    if (sym != null && !sym.isStatic() && ((sym.flags() & Flags.SYNTHETIC) == 0) && methodSymbol.overrides(sym,(TypeSymbol)methodSymbol.owner,types,true)) {
      return (MethodSymbol)sym;
    }
  }
  return null;
}
