/** 
 * {@inheritDoc}
 */
@Override public String resolve(String expression){
  ValueExpression expr=expressionFactory.createValueExpression(elContext,expression,String.class);
  return String.valueOf(expr.getValue(elContext));
}
