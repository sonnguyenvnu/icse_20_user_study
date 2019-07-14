private static boolean isNumericMethodCall(MethodInvocationTree tree,VisitorState state){
  List<VarSymbol> params=getSymbol(tree).getParameters();
  if (params.size() == 1) {
    Type type0=params.get(0).asType();
    return isSameType(type0,state.getSymtab().intType,state) || isSameType(type0,state.getSymtab().longType,state) || isSameType(type0,state.getSymtab().doubleType,state);
  }
  return false;
}
