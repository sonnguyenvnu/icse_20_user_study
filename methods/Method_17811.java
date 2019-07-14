/** 
 * Only use if absolutely needed! This removes the cached layout so this component will be remeasured even if it has alread been measured with the same size specs.
 */
public void clearCachedLayout(){
  if (getCachedLayout() != null) {
    mThreadIdToLastMeasuredLayout.remove(Thread.currentThread().getId());
  }
}
