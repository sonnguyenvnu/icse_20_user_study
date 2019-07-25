@Override protected void dispose(){
  SModel oldModel=myChangeSet.getOldModel();
  SModel newModel=myChangeSet.getNewModel();
  oldModel.removeChangeListener(myChangeListener);
  if (newModel != oldModel) {
    newModel.removeChangeListener(myChangeListener);
  }
  if (myNodeDifferencePane != null) {
    myNodeDifferencePane.dispose();
  }
  super.dispose();
}
