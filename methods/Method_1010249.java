/** 
 * Note: the actual dispose is called asynchronously in the EDT. The motive is to allow a ClassLoading client to dispose asynchronously in the Event Dispatch Thread.
 */
public void dispose(){
  myDisposed=true;
  myClasses.clear();
  if (myDependenciesClassLoaders != null) {
    myDependenciesClassLoaders.clear();
  }
}
