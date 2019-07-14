@Override public Description matchMethodInvocation(MethodInvocationTree tree,VisitorState state){
  if (!FENCE_MATCHER.matches(tree,state)) {
    return NO_MATCH;
  }
  Tree previous=null;
  OUTER:   for (  Tree enclosing : state.getPath().getParentPath()) {
switch (enclosing.getKind()) {
case TRY:
      if (((TryTree)enclosing).getFinallyBlock().equals(previous)) {
        return NO_MATCH;
      }
    break;
case CLASS:
case METHOD:
case LAMBDA_EXPRESSION:
  break OUTER;
default :
}
previous=enclosing;
}
return describeMatch(tree);
}
