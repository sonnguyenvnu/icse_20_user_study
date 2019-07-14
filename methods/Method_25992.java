private boolean matchAnySuperType(ClassTree tree,VisitorState state){
  List<Tree> superTypes=Lists.<Tree>newArrayList(tree.getImplementsClause());
  Tree superClass=tree.getExtendsClause();
  if (superClass != null) {
    superTypes.add(superClass);
  }
  return superTypes.stream().anyMatch(superType -> ITERABLE_AND_ITERATOR_MATCHER.matches(superType,state));
}
