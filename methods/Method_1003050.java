/** 
 * Execute the query, writing the result to the target result.
 * @param limit the maximum number of rows to return
 * @param target the target result (null will return the result)
 * @return the result set (if the target is not set).
 */
public final ResultInterface query(int limit,ResultTarget target){
  if (isUnion()) {
    return queryWithoutCacheLazyCheck(limit,target);
  }
  fireBeforeSelectTriggers();
  if (noCache || !session.getDatabase().getOptimizeReuseResults() || (session.isLazyQueryExecution() && !neverLazy)) {
    return queryWithoutCacheLazyCheck(limit,target);
  }
  Value[] params=getParameterValues();
  long now=session.getDatabase().getModificationDataId();
  if (isEverything(ExpressionVisitor.DETERMINISTIC_VISITOR)) {
    if (lastResult != null && !lastResult.isClosed() && limit == lastLimit) {
      if (sameResultAsLast(session,params,lastParameters,lastEvaluated)) {
        lastResult=lastResult.createShallowCopy(session);
        if (lastResult != null) {
          lastResult.reset();
          return lastResult;
        }
      }
    }
  }
  lastParameters=params;
  closeLastResult();
  ResultInterface r=queryWithoutCacheLazyCheck(limit,target);
  lastResult=r;
  this.lastEvaluated=now;
  lastLimit=limit;
  return r;
}
