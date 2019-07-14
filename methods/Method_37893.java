/** 
 * Emulates <code>Reflection.getCallerClass</code> using standard API. This implementation uses custom <code>SecurityManager</code> and it is the fastest. Other implementations are: <ul> <li><code>new Throwable().getStackTrace()[callStackDepth]</code></li> <li><code>Thread.currentThread().getStackTrace()[callStackDepth]</code> (the slowest)</li> </ul> <p> In case when usage of <code>SecurityManager</code> is not allowed, this method fails back to the second implementation. <p> Note that original <code>Reflection.getCallerClass</code> is way faster then any emulation.
 */
public static Class getCallerClass(int framesToSkip){
  if (SECURITY_MANAGER != null) {
    return SECURITY_MANAGER.getCallerClass(framesToSkip);
  }
  StackTraceElement[] stackTraceElements=new Throwable().getStackTrace();
  if (framesToSkip >= 2) {
    framesToSkip+=4;
  }
  String className=stackTraceElements[framesToSkip].getClassName();
  try {
    return Thread.currentThread().getContextClassLoader().loadClass(className);
  }
 catch (  ClassNotFoundException cnfex) {
    throw new UnsupportedOperationException(className + " not found.");
  }
}
