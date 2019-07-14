private static int findIndexOfFormatStringParameter(VisitorState state,MethodSymbol symbol){
  int indexOfFirstString=-1;
  List<VarSymbol> params=symbol.params();
  for (int i=0; i < params.size(); i++) {
    VarSymbol varSymbol=params.get(i);
    if (ASTHelpers.hasAnnotation(varSymbol,FormatString.class,state)) {
      return i;
    }
    if (indexOfFirstString == -1 && ASTHelpers.isSameType(varSymbol.type,state.getSymtab().stringType,state)) {
      indexOfFirstString=i;
    }
  }
  return indexOfFirstString;
}
