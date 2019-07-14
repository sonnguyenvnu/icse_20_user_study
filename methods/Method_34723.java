/** 
 * Given all of the thread pool configuration, what is the actual maximumSize applied to the thread pool via  {@link ThreadPoolExecutor#setMaximumPoolSize(int)}Cases: 1) allowMaximumSizeToDivergeFromCoreSize == false: maximumSize is set to coreSize 2) allowMaximumSizeToDivergeFromCoreSize == true, maximumSize >= coreSize: thread pool has different core/max sizes, so return the configured max 3) allowMaximumSizeToDivergeFromCoreSize == true, maximumSize < coreSize: threadpool incorrectly configured, use coreSize for max size
 * @return actually configured maximum size of threadpool
 */
public Integer actualMaximumSize(){
  final int coreSize=coreSize().get();
  final int maximumSize=maximumSize().get();
  if (getAllowMaximumSizeToDivergeFromCoreSize().get()) {
    if (coreSize > maximumSize) {
      return coreSize;
    }
 else {
      return maximumSize;
    }
  }
 else {
    return coreSize;
  }
}
