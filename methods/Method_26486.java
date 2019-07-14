private void lambdaToMethod(VisitorState state,LambdaExpressionTree lambda,SuggestedFix.Builder fix,String name,Tree type){
  Type fi=state.getTypes().findDescriptorType(getType(type));
  Tree tree=state.getPath().getLeaf();
  ModifiersTree modifiers=getModifiers(tree);
  int endPosition=state.getEndPosition(tree);
  StringBuilder replacement=new StringBuilder();
  replacement.append(String.format(" %s %s(",prettyType(state,fix,fi.getReturnType()),name));
  replacement.append(Streams.zip(fi.getParameterTypes().stream(),lambda.getParameters().stream(),(t,p) -> String.format("%s %s",prettyType(state,fix,t),p.getName())).collect(joining(", ")));
  replacement.append(")");
  if (lambda.getBody().getKind() == Kind.BLOCK) {
    replacement.append(state.getSourceForNode(lambda.getBody()));
  }
 else {
    replacement.append("{");
    if (!fi.getReturnType().hasTag(TypeTag.VOID)) {
      replacement.append("return ");
    }
    replacement.append(state.getSourceForNode(lambda.getBody()));
    replacement.append(";");
    replacement.append("}");
  }
  fix.replace(state.getEndPosition(modifiers) + 1,endPosition,replacement.toString());
}
