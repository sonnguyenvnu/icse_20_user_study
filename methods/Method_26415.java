private static boolean isLongTimeUnitMethodCall(MethodInvocationTree tree,VisitorState state){
  Type longType=state.getSymtab().longType;
  Type timeUnitType=state.getTypeFromString("java.util.concurrent.TimeUnit");
  List<VarSymbol> params=getSymbol(tree).getParameters();
  if (params.size() == 2) {
    return isSameType(params.get(0).asType(),longType,state) && isSameType(params.get(1).asType(),timeUnitType,state);
  }
  return false;
}
