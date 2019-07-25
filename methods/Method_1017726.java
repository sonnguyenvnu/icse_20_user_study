private boolean evaluate(Object value){
  evaluator.bind(value);
  boolean result=evaluator.evaluate();
  if (result)   ++successfulEvaluations;
 else   ++discards;
  if (tooManyDiscards())   throw new DiscardRatioExceededException(parameter,discards,successfulEvaluations);
  return result;
}
