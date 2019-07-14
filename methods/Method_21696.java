/** 
 * ??????
 * @param cond
 * @return
 * @throws SqlParseException
 */
protected ToXContent make(Condition cond) throws SqlParseException {
  String name=cond.getName();
  Object value=cond.getValue();
  ToXContent x=null;
  if (value instanceof SQLMethodInvokeExpr) {
    x=make(cond,name,(SQLMethodInvokeExpr)value);
  }
 else   if (value instanceof SubQueryExpression) {
    x=make(cond,name,((SubQueryExpression)value).getValues());
  }
 else {
    x=make(cond,name,value);
  }
  return x;
}
