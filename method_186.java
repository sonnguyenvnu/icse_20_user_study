/** 
 * Utility method to use SafeRunnable from lambdas. <p>Eg: <pre> <code> executor.submit(SafeRunnable.safeRun(() -> { // My not-safe code }); </code> </pre>
 */
public static SafeRunnable _XXXXX_(Runnable runnable){
  return new SafeRunnable(){
    @Override public void _XXXXX_(){
      runnable.run();
    }
  }
;
}