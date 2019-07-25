/** 
 * Disable  {@link RequestContext} during operators.
 */
public static synchronized void disable(){
  if (!enabled) {
    return;
  }
  RxJavaPlugins.setOnObservableAssembly(oldOnObservableAssembly);
  oldOnObservableAssembly=null;
  RxJavaPlugins.setOnConnectableObservableAssembly(oldOnConnectableObservableAssembly);
  oldOnConnectableObservableAssembly=null;
  RxJavaPlugins.setOnCompletableAssembly(oldOnCompletableAssembly);
  oldOnCompletableAssembly=null;
  RxJavaPlugins.setOnSingleAssembly(oldOnSingleAssembly);
  oldOnSingleAssembly=null;
  RxJavaPlugins.setOnMaybeAssembly(oldOnMaybeAssembly);
  oldOnMaybeAssembly=null;
  RxJavaPlugins.setOnFlowableAssembly(oldOnFlowableAssembly);
  oldOnFlowableAssembly=null;
  RxJavaPlugins.setOnConnectableFlowableAssembly(oldOnConnectableFlowableAssembly);
  oldOnConnectableFlowableAssembly=null;
  RxJavaPlugins.setOnParallelAssembly(oldOnParallelAssembly);
  oldOnParallelAssembly=null;
  enabled=false;
}
