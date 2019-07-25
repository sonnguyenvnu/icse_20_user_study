/** 
 * {@inheritDoc}
 */
@Override public String resolve(String expression){
  ClassLoader tccl=Thread.currentThread().getContextClassLoader();
  try {
    ClassLoader newTccl=classLoader == null ? ELExpressionFactory.class.getClassLoader() : classLoader;
    Thread.currentThread().setContextClassLoader(newTccl);
    ValueExpression expr=expressionFactory.createValueExpression(elContext,expression,String.class);
    return String.valueOf(expr.getValue(elContext));
  }
  finally {
    Thread.currentThread().setContextClassLoader(tccl);
  }
}
