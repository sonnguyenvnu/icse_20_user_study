private Description handle(@Nullable DocTreePath docTreePath,VisitorState state){
  if (docTreePath == null) {
    return NO_MATCH;
  }
  if (!requiresJavadoc(docTreePath.getTreePath().getLeaf(),state)) {
    return Description.NO_MATCH;
  }
  List<? extends DocTree> firstSentence=docTreePath.getDocComment().getFirstSentence();
  if (!firstSentence.isEmpty()) {
    return NO_MATCH;
  }
  Symbol symbol=getSymbol(docTreePath.getTreePath().getLeaf());
  if (symbol == null) {
    return NO_MATCH;
  }
  if (symbol.isConstructor()) {
    return NO_MATCH;
  }
  ReturnTree returnTree=findFirst(docTreePath,ReturnTree.class);
  if (returnTree != null) {
    return generateReturnFix(docTreePath,returnTree,state);
  }
  SeeTree seeTree=findFirst(docTreePath,SeeTree.class);
  if (seeTree != null) {
    return generateSeeFix(docTreePath,seeTree,state);
  }
  Set<Modifier> modifiers=symbol.getModifiers();
  if (!modifiers.contains(Modifier.PUBLIC) && !modifiers.contains(Modifier.PROTECTED)) {
    return NO_MATCH;
  }
  if (hasAnnotation(symbol,"java.lang.Override",state) || hasAnnotation(symbol,"java.lang.Deprecated",state)) {
    return NO_MATCH;
  }
  return buildDescription(diagnosticPosition(docTreePath,state)).build();
}
