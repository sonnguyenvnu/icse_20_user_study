/** 
 * Start constructing an await statement with some settings. E.g. <p> <pre> with().pollInterval(20, MILLISECONDS).await().until(somethingHappens()); </pre>
 * @return the condition factory
 */
public static ConditionFactory with(){
  return new ConditionFactory(null,defaultWaitConstraint,defaultPollInterval,defaultPollDelay,defaultCatchUncaughtExceptions,defaultExceptionIgnorer,defaultConditionEvaluationListener,defaultExecutorLifecycle);
}
