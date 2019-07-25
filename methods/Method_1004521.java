/** 
 * Await forever until the condition is satisfied. Caution: You can block subsequent tests and the entire build can hang indefinitely, it's recommended to always use a timeout.
 * @return the condition factory
 */
public ConditionFactory forever(){
  return new ConditionFactory(alias,AtMostWaitConstraint.FOREVER,pollInterval,pollDelay,catchUncaughtExceptions,exceptionsIgnorer,conditionEvaluationListener,executorLifecycle);
}
