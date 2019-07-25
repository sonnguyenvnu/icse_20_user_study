private void init(){
  myModelListenerManager.track(myRootNode);
  if (myClassManager != null) {
    myClassManager.addListener(myClassesListener);
  }
}
