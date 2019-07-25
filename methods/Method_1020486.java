@Override public JsDocCastExpression clone(){
  return new JsDocCastExpression(expression.clone(),castType);
}
