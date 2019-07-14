public SimpleDictionaryParser<V> addToValueExpression(String id,String expression){
  toValueExpressions.put(id,expression);
  return this;
}
