static boolean equivalentExprs(Unifier unifier,JCExpression expr1,JCExpression expr2){
  return expr1.type != null && expr2.type != null && Types.instance(unifier.getContext()).isSameType(expr2.type,expr1.type) && expr2.toString().equals(expr1.toString());
}
