@Override public Description matchMethod(MethodTree tree,VisitorState state){
  ClassTree enclosingClazz=ASTHelpers.findEnclosingNode(state.getPath(),ClassTree.class);
  if (tree.getModifiers().getFlags().contains(Modifier.DEFAULT) && IS_FUNCTIONAL_INTERFACE.matches(enclosingClazz,state)) {
    Types types=state.getTypes();
    Set<Symbol> functionalSuperInterfaceSams=enclosingClazz.getImplementsClause().stream().filter(t -> IS_FUNCTIONAL_INTERFACE.matches(t,state)).map(ASTHelpers::getSymbol).map(TypeSymbol.class::cast).map(types::findDescriptorSymbol).collect(toImmutableSet());
    Symbol thisInterfaceSam=types.findDescriptorSymbol(ASTHelpers.getSymbol(enclosingClazz));
    TreeVisitor<Boolean,VisitorState> behaviorPreserving=new BehaviorPreservingChecker(thisInterfaceSam);
    if (!Collections.disjoint(ASTHelpers.findSuperMethods(ASTHelpers.getSymbol(tree),types),functionalSuperInterfaceSams) && !tree.accept(behaviorPreserving,state)) {
      return describeMatch(tree);
    }
  }
  return Description.NO_MATCH;
}
