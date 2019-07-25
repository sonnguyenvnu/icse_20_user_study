@Override public void changed(DataSource source,Iterable<String> changedItems){
  assertCanChange();
  SModel oldModel=getCurrentModelInternal();
  if (oldModel == null) {
    return;
  }
  MapSequence.fromMap(myRootsPerFile).clear();
  MapSequence.fromMap(myRootsById).clear();
  replace(createModel());
}
