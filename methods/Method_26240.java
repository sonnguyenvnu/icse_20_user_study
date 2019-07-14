@Override public Description matchMethod(MethodTree tree,VisitorState state){
  MethodSymbol methodSymbol=getSymbol(tree);
  if (methodSymbol == null) {
    return NO_MATCH;
  }
  Optional<MethodSymbol> maybeSuperMethod=findSuperMethod(methodSymbol,state.getTypes());
  if (!maybeSuperMethod.isPresent()) {
    return NO_MATCH;
  }
  MethodSymbol superMethod=maybeSuperMethod.get();
  if (tree.getBody() == null || tree.getBody().getStatements().size() != 1) {
    return NO_MATCH;
  }
  StatementTree statement=tree.getBody().getStatements().get(0);
  ExpressionTree expression=getSingleInvocation(statement);
  if (expression == null) {
    return NO_MATCH;
  }
  MethodInvocationTree methodInvocationTree=(MethodInvocationTree)expression;
  if (!getSymbol(methodInvocationTree).equals(superMethod)) {
    return NO_MATCH;
  }
  ExpressionTree receiver=getReceiver(methodInvocationTree);
  if (!(receiver instanceof IdentifierTree)) {
    return NO_MATCH;
  }
  if (!((IdentifierTree)receiver).getName().contentEquals("super")) {
    return NO_MATCH;
  }
  DocCommentTree docCommentTree=JavacTrees.instance(state.context).getDocCommentTree(state.getPath());
  if (docCommentTree != null) {
    return NO_MATCH;
  }
  if (!methodSymbol.getModifiers().equals(superMethod.getModifiers())) {
    return NO_MATCH;
  }
  if (methodSymbol.getModifiers().contains(Modifier.PROTECTED) && !Objects.equals(superMethod.packge(),methodSymbol.packge())) {
    return NO_MATCH;
  }
  ImmutableSet<Symbol> superAnnotations=getAnnotations(superMethod);
  ImmutableSet<Symbol> methodAnnotations=getAnnotations(methodSymbol);
  if (!Sets.difference(Sets.symmetricDifference(superAnnotations,methodAnnotations),ImmutableSet.of(state.getSymtab().overrideType.tsym)).isEmpty()) {
    return NO_MATCH;
  }
  for (int i=0; i < tree.getParameters().size(); ++i) {
    if (!(methodInvocationTree.getArguments().get(i) instanceof IdentifierTree)) {
      return NO_MATCH;
    }
    if (!getSymbol(tree.getParameters().get(i)).equals(getSymbol(methodInvocationTree.getArguments().get(i)))) {
      return NO_MATCH;
    }
  }
  if (state.getOffsetTokensForNode(tree.getBody()).stream().anyMatch(t -> !t.comments().isEmpty())) {
    return NO_MATCH;
  }
  return describeMatch(tree,SuggestedFix.delete(tree));
}
