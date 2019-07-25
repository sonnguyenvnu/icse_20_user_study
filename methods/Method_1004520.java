/** 
 * Set the alias
 * @param alias alias
 * @return the condition factory
 * @see org.awaitility.Awaitility#await(String)
 */
public ConditionFactory alias(String alias){
  return new ConditionFactory(alias,timeoutConstraint,pollInterval,pollDelay,catchUncaughtExceptions,exceptionsIgnorer,conditionEvaluationListener,executorLifecycle);
}
