@Override public Description matchMethod(MethodTree tree,VisitorState state){
  Type stringType=state.getSymtab().stringType;
  boolean isFormatMethod=ASTHelpers.hasAnnotation(ASTHelpers.getSymbol(tree),FormatMethod.class,state);
  boolean foundFormatString=false;
  boolean foundString=false;
  for (  VariableTree param : tree.getParameters()) {
    VarSymbol paramSymbol=ASTHelpers.getSymbol(param);
    boolean isStringParam=ASTHelpers.isSameType(paramSymbol.type,stringType,state);
    if (isStringParam) {
      foundString=true;
    }
    if (ASTHelpers.hasAnnotation(paramSymbol,FormatString.class,state)) {
      if (!isFormatMethod) {
        return buildDescription(tree).setMessage("A parameter can only be annotated @FormatString in a method annotated " + "@FormatMethod: " + state.getSourceForNode(param)).build();
      }
      if (!isStringParam) {
        return buildDescription(param).setMessage("Only strings can be annotated @FormatString.").build();
      }
      if (foundFormatString) {
        return buildDescription(tree).setMessage("A method cannot have more than one @FormatString parameter.").build();
      }
      foundFormatString=true;
    }
  }
  if (isFormatMethod && !foundString) {
    return buildDescription(tree).setMessage("An @FormatMethod must contain at least one String parameter.").build();
  }
  return Description.NO_MATCH;
}
