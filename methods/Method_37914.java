/** 
 * Wraps a task into a runnable.
 */
public default Runnable toRunnable(){
  return () -> {
    try {
      run();
    }
 catch (    Exception e) {
      throw new UncheckedException(e);
    }
  }
;
}
