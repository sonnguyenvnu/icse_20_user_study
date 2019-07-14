@Override public Description matchClass(ClassTree tree,VisitorState state){
  if (COMPARABLE_AND_COMPARATOR_MATCHER.matches(tree,state)) {
    if (matchAnySuperType(tree,state)) {
      return Description.NO_MATCH;
    }
    ClassSymbol symbol=getSymbol(tree);
    if (symbol.isEnum()) {
      return Description.NO_MATCH;
    }
    return describeMatch(tree);
  }
  return Description.NO_MATCH;
}
