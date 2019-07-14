/** 
 * Call from tests to clear external references. 
 */
public static void clearMountContentPools(){
synchronized (sMountContentLock) {
    sMountContentPoolsByContext.clear();
  }
}
