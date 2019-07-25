/** 
 * <p>await.</p>
 * @return a T object.
 */
public T await(){
  conditionAwaiter.await(conditionEvaluationHandler);
  return lastResult;
}
