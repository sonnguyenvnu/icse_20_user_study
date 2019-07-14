/** 
 * Creates a  {@link Statement} that will run the given{@code statement}, and timeout the operation based on the values configured in this rule. Subclasses can override this method for different behavior.
 * @since 4.12
 */
protected Statement createFailOnTimeoutStatement(Statement statement) throws Exception {
  return FailOnTimeout.builder().withTimeout(timeout,timeUnit).withLookingForStuckThread(lookForStuckThread).build(statement);
}
