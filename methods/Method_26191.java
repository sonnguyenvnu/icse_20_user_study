private static boolean finalNoOverrides(Type type,VisitorState state){
  if (type == null) {
    return false;
  }
  if (!type.isFinal()) {
    return false;
  }
  Types types=state.getTypes();
  Names names=state.getNames();
  MethodSymbol toString=(MethodSymbol)state.getSymtab().objectType.tsym.members().findFirst(names.toString);
  return Iterables.isEmpty(types.membersClosure(type,false).getSymbolsByName(names.toString,m -> m != toString && m.overrides(toString,type.tsym,types,false)));
}
