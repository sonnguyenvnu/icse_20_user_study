/** 
 * Unwraps a command that was previously wrapped by  {@link #preserveContext(Runnable)}.
 */
public Runnable unwrap(Runnable command){
  if (command instanceof ContextPreservingAbstractRunnable) {
    return ((ContextPreservingAbstractRunnable)command).unwrap();
  }
  if (command instanceof ContextPreservingRunnable) {
    return ((ContextPreservingRunnable)command).unwrap();
  }
  return command;
}
