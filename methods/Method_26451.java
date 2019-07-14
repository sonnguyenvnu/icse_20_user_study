private static boolean hasInitialStringParameter(MethodSymbol sym,VisitorState state){
  Types types=state.getTypes();
  List<VarSymbol> parameters=sym.getParameters();
  return !parameters.isEmpty() && types.isSameType(parameters.get(0).type,state.getSymtab().stringType);
}
