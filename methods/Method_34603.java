/** 
 * Returns true iff the t was caused by a java.lang.Error that is unrecoverable.  Note: not all java.lang.Errors are unrecoverable.
 * @see <a href="https://github.com/Netflix/Hystrix/issues/713"></a> for more contextSolution taken from <a href="https://github.com/ReactiveX/RxJava/issues/748"></a> The specific set of Error that are considered unrecoverable are: <ul> <li> {@code StackOverflowError}</li> <li> {@code VirtualMachineError}</li> <li> {@code ThreadDeath}</li> <li> {@code LinkageError}</li> </ul>
 * @param t throwable to check
 * @return true iff the t was caused by a java.lang.Error that is unrecoverable
 */
private boolean isUnrecoverable(Throwable t){
  if (t != null && t.getCause() != null) {
    Throwable cause=t.getCause();
    if (cause instanceof StackOverflowError) {
      return true;
    }
 else     if (cause instanceof VirtualMachineError) {
      return true;
    }
 else     if (cause instanceof ThreadDeath) {
      return true;
    }
 else     if (cause instanceof LinkageError) {
      return true;
    }
  }
  return false;
}
