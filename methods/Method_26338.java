static ImmutableSet<String> getGuardValues(Tree tree,VisitorState state){
  Symbol sym=getSymbol(tree);
  if (sym == null) {
    return null;
  }
  return getAnnotationValueAsStrings(sym);
}
