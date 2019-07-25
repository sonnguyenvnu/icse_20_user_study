public void init(){
  myGenStatusVisitor.setUpdater(myUpdater);
  myErrorVisitor.setUpdater(myUpdater);
  myModifiedMarker.setUpdater(myUpdater);
  myTree.addTreeNodeListener(myNodeListener);
}
