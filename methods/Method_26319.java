private boolean enclosingInstance(GuardedByExpression expr){
  while (expr.kind() == Kind.SELECT) {
    expr=((Select)expr).base();
    if (expr.kind() == Kind.THIS) {
      return true;
    }
  }
  return false;
}
