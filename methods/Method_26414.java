private static boolean isJodaDurationMethodCall(MethodInvocationTree tree,VisitorState state){
  Type jodaDurationType=state.getTypeFromString("org.joda.time.ReadableDuration");
  List<VarSymbol> params=getSymbol(tree).getParameters();
  if (params.size() == 1) {
    return isSubtype(params.get(0).asType(),jodaDurationType,state);
  }
  return false;
}
