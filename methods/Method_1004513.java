/** 
 * Start building a named await statement. This is useful is cases when you have several awaits in your test and you need to tell them apart. If a named await timeout's the <code>alias</code> will be displayed indicating which await statement that failed.
 * @param alias the alias that will be shown if the await timeouts.
 * @return the condition factory
 */
public static ConditionFactory await(String alias){
  return new ConditionFactory(alias,defaultWaitConstraint,defaultPollInterval,defaultPollDelay,defaultCatchUncaughtExceptions,defaultExceptionIgnorer,defaultConditionEvaluationListener,defaultExecutorLifecycle);
}
