@Override public UExpression negate(){
  return (getKind() == Kind.LOGICAL_COMPLEMENT) ? getExpression() : super.negate();
}
