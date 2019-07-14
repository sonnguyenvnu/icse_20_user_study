@Override public Description matchMethod(MethodTree tree,VisitorState state){
  MultiMatchResult<AnnotationTree> matchResult=CLASS_INIT_ANNOTATION.multiMatchResult(tree,state);
  if (!matchResult.matches() || isStatic().matches(tree,state)) {
    return Description.NO_MATCH;
  }
  return buildDescription(tree).setMessage(messageForAnnos(matchResult.matchingNodes())).addFix(SuggestedFixes.addModifiers(tree,state,Modifier.STATIC)).build();
}
