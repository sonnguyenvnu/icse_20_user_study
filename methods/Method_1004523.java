/** 
 * Await for an asynchronous operation and give this await instance a particular name. This is useful in cases when you have several await statements in one test and you want to know which one that fails (the alias will be shown if a timeout exception occurs).
 * @param alias the alias
 * @return the condition factory
 */
public ConditionFactory await(String alias){
  return new ConditionFactory(alias,timeoutConstraint,pollInterval,pollDelay,catchUncaughtExceptions,exceptionsIgnorer,conditionEvaluationListener,executorLifecycle);
}
