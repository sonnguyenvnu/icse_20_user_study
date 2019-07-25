/** 
 * Disable the assembly tracking.
 */
public static void disable(){
  if (lock.compareAndSet(false,true)) {
    RxJavaPlugins.setOnCompletableAssembly(null);
    RxJavaPlugins.setOnSingleAssembly(null);
    RxJavaPlugins.setOnMaybeAssembly(null);
    RxJavaPlugins.setOnObservableAssembly(null);
    RxJavaPlugins.setOnFlowableAssembly(null);
    RxJavaPlugins.setOnConnectableObservableAssembly(null);
    RxJavaPlugins.setOnConnectableFlowableAssembly(null);
    RxJavaPlugins.setOnParallelAssembly(null);
    lock.set(false);
  }
}
