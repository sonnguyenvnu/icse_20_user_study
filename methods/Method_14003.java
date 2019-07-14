@Override public PropertyIdValue evaluate(ExpressionContext ctxt){
  return new SuggestedPropertyIdValue(pid,ctxt.getBaseIRI(),label);
}
