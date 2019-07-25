/** 
 * Tries to unmap memory for the specified byte buffer using Java internals in unsafe way if  {@link SysProperties#NIO_CLEANER_HACK} is enabled andaccess is not denied by a security manager.
 * @param buffer mapped byte buffer
 * @return whether operation was successful
 */
public static boolean unmap(ByteBuffer buffer){
  if (!ENABLED) {
    return false;
  }
  try {
    if (INVOKE_CLEANER != null) {
      INVOKE_CLEANER.invoke(UNSAFE,buffer);
      return true;
    }
    Method cleanerMethod=buffer.getClass().getMethod("cleaner");
    cleanerMethod.setAccessible(true);
    Object cleaner=cleanerMethod.invoke(buffer);
    if (cleaner != null) {
      Method clearMethod=cleaner.getClass().getMethod("clean");
      clearMethod.invoke(cleaner);
    }
    return true;
  }
 catch (  Throwable e) {
    return false;
  }
}
