@Override public Description matchMethodInvocation(MethodInvocationTree tree,VisitorState state){
  if (!STRING_FORMAT.matches(tree,state)) {
    return Description.NO_MATCH;
  }
  if (tree.getArguments().size() != 2) {
    return Description.NO_MATCH;
  }
  VarSymbol formatString=asSymbol(tree.getArguments().get(0));
  VarSymbol formatArgs=asSymbol(tree.getArguments().get(1));
  if (formatString == null || formatArgs == null) {
    return Description.NO_MATCH;
  }
  MethodTree enclosingMethod=ASTHelpers.findEnclosingNode(state.getPath(),MethodTree.class);
  if (enclosingMethod == null || !ASTHelpers.getSymbol(enclosingMethod).isVarArgs() || ASTHelpers.hasAnnotation(enclosingMethod,FormatMethod.class,state)) {
    return Description.NO_MATCH;
  }
  List<? extends VariableTree> enclosingParameters=enclosingMethod.getParameters();
  Optional<? extends VariableTree> formatParameter=findParameterWithSymbol(enclosingParameters,formatString);
  Optional<? extends VariableTree> argumentsParameter=findParameterWithSymbol(enclosingParameters,formatArgs);
  if (!formatParameter.isPresent() || !argumentsParameter.isPresent()) {
    return Description.NO_MATCH;
  }
  if (!argumentsParameter.get().equals(getLast(enclosingParameters))) {
    return Description.NO_MATCH;
  }
  boolean fixable=formatParameter.get().equals(enclosingParameters.get(enclosingParameters.size() - 2));
  return buildDescription(enclosingMethod).setMessage(fixable ? message() : (message() + REORDER)).build();
}
