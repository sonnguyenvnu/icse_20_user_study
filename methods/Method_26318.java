private static Select findOuterInstance(GuardedByExpression expr){
  while (expr.kind() == Kind.SELECT) {
    Select select=(Select)expr;
    if (select.sym().name.contentEquals(GuardedByExpression.ENCLOSING_INSTANCE_NAME)) {
      return select;
    }
    expr=select.base();
  }
  return null;
}
