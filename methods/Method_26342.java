public static Symbol bindGuardedByString(Tree tree,String guard,VisitorState visitorState){
  Optional<GuardedByExpression> bound=GuardedByBinder.bindString(guard,GuardedBySymbolResolver.from(tree,visitorState));
  if (!bound.isPresent()) {
    return null;
  }
  return bound.get().sym();
}
