public void dispose(){
  myTree.removeTreeNodeListener(myNodeListener);
  if (myModuleListeners != null) {
    myModuleListeners.stopListening();
    myModuleListeners=null;
  }
  if (myModelListeners != null) {
    myModelListeners.stopListening(myProjectRepository,myGenStatusVisitor.getStatusManager());
    myModelListeners=null;
  }
  myExecutor.shutdownNow();
  myGenStatusVisitor.setUpdater(null);
  myErrorVisitor.setUpdater(null);
  myModifiedMarker.setUpdater(null);
}
