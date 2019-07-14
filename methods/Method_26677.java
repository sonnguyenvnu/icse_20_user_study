@Override public UExpression negate(){
  checkState(getKind() == Kind.BOOLEAN_LITERAL,"Cannot negate a non-Boolean literal");
  return booleanLit(!((Boolean)getValue()));
}
