/** 
 * Disables the validation hooks be resetting the assembly hooks to none.
 */
public static void disable(){
  RxJavaPlugins.setOnCompletableAssembly(null);
  RxJavaPlugins.setOnSingleAssembly(null);
  RxJavaPlugins.setOnMaybeAssembly(null);
  RxJavaPlugins.setOnObservableAssembly(null);
  RxJavaPlugins.setOnFlowableAssembly(null);
  RxJavaPlugins.setOnConnectableObservableAssembly(null);
  RxJavaPlugins.setOnConnectableFlowableAssembly(null);
  RxJavaPlugins.setOnParallelAssembly(null);
  enabled=false;
}
