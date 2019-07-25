@SuppressWarnings("unchecked") @Override public K convert(V item){
  return (K)parsedExpression.getValue(item);
}
