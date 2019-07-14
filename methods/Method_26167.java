@Override public Description matchMethodInvocation(MethodInvocationTree tree,VisitorState state){
  if (!MATCHER.matches(tree,state)) {
    return NO_MATCH;
  }
  Type classType=getType(getOnlyElement(tree.getArguments()));
  if (classType == null || classType.getTypeArguments().isEmpty()) {
    return NO_MATCH;
  }
  Type type=getUpperBound(getOnlyElement(classType.getTypeArguments()),state.getTypes());
  if (isSameType(type,state.getSymtab().annotationType,state)) {
    return NO_MATCH;
  }
  RetentionPolicy retention=state.getTypes().getRetention(type.asElement());
switch (retention) {
case RUNTIME:
    break;
case SOURCE:
case CLASS:
  return buildDescription(tree).setMessage(String.format("%s; %s has %s retention",message(),type.asElement().getSimpleName(),retention)).build();
}
return NO_MATCH;
}
