private static Type predicateType(Type type,VisitorState state){
  Symbol predicate=state.getSymbolFromString(java.util.function.Predicate.class.getName());
  if (predicate == null) {
    return null;
  }
  Type asPredicate=state.getTypes().asSuper(type,predicate);
  if (asPredicate == null) {
    return null;
  }
  return getOnlyElement(asPredicate.getTypeArguments(),null);
}
