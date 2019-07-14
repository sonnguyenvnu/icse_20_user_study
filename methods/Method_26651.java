public UExpression negate(){
  return UUnary.create(Kind.LOGICAL_COMPLEMENT,this);
}
