private static GuardedByExpression bind(JCTree.JCExpression exp,BinderContext context){
  GuardedByExpression expr=BINDER.visit(exp,context);
  checkGuardedBy(expr != null,String.valueOf(exp));
  checkGuardedBy(expr.kind() != Kind.TYPE_LITERAL,"Raw type literal: %s",exp);
  return expr;
}
