/** 
 * Start constructing an await statement given some settings. E.g. <p> <pre> given().pollInterval(20, MILLISECONDS).then().await().until(somethingHappens()); </pre>
 * @return the condition factory
 */
public static ConditionFactory given(){
  return new ConditionFactory(null,defaultWaitConstraint,defaultPollInterval,defaultPollDelay,defaultCatchUncaughtExceptions,defaultExceptionIgnorer,defaultConditionEvaluationListener,defaultExecutorLifecycle);
}
