public static SQLMethodInvokeExpr makeBinaryMethodField(SQLBinaryOpExpr expr,String alias,boolean first) throws SqlParseException {
  List<SQLExpr> params=new ArrayList<>();
  String scriptFieldAlias;
  if (first && (alias == null || alias.equals(""))) {
    scriptFieldAlias="field_" + SQLFunctions.random();
  }
 else {
    scriptFieldAlias=alias;
  }
  params.add(new SQLCharExpr(scriptFieldAlias));
switch (expr.getOperator()) {
case Add:
    return convertBinaryOperatorToMethod("add",expr);
case Multiply:
  return convertBinaryOperatorToMethod("multiply",expr);
case Divide:
return convertBinaryOperatorToMethod("divide",expr);
case Modulus:
return convertBinaryOperatorToMethod("modulus",expr);
case Subtract:
return convertBinaryOperatorToMethod("subtract",expr);
default :
throw new SqlParseException(expr.getOperator().getName() + " is not support");
}
}
