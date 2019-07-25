/** 
 * Provides the currently executing execution.
 * @return the currently executing execution
 * @throws UnmanagedThreadException if called from outside of an execution
 * @see #currentOpt()
 */
static Execution current() throws UnmanagedThreadException {
  return DefaultExecution.require();
}
