@Override public ItemIdValue evaluate(ExpressionContext ctxt){
  return new SuggestedItemIdValue(qid,ctxt.getBaseIRI(),label);
}
