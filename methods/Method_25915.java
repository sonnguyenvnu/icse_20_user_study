private static boolean canBeUsedByGetChecked(MethodSymbol constructor,VisitorState state){
  Type stringType=state.getSymtab().stringType;
  Type throwableType=state.getSymtab().throwableType;
  if (!constructor.getModifiers().contains(PUBLIC)) {
    return false;
  }
  for (  VarSymbol param : constructor.getParameters()) {
    if (!isSameType(param.asType(),stringType,state) && !isSameType(param.asType(),throwableType,state)) {
      return false;
    }
  }
  return true;
}
