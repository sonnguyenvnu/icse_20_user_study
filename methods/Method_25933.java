@Override public Description matchNewClass(NewClassTree tree,VisitorState state){
  if (!MATCHER.matches(tree,state)) {
    return NO_MATCH;
  }
  Type type;
  ASTHelpers.TargetType targetType=ASTHelpers.targetType(state);
  if (targetType != null) {
    type=targetType.type();
  }
 else {
    type=getType(tree.getIdentifier());
  }
  List<Type> typeArguments=type.getTypeArguments();
  if (typeArguments.isEmpty()) {
    return NO_MATCH;
  }
  Type keyType=typeArguments.get(0);
  if (ASTHelpers.isCastable(keyType,state.getSymtab().comparableType,state)) {
    return NO_MATCH;
  }
  return buildDescription(tree).setMessage(String.format("%s does not implement Comparable",keyType)).build();
}
