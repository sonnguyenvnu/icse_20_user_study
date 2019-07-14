private Optional<Fix> getFix(ExpressionTree tree,VisitorState state,Tree parent,ToStringKind toStringKind){
switch (toStringKind) {
case IMPLICIT:
case FLOGGER:
    return implicitToStringFix(tree,state);
case EXPLICIT:
  return toStringFix(parent,tree,state);
case NONE:
}
throw new AssertionError();
}
