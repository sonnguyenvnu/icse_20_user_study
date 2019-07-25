@Override public void load(){
  if (getLoadingState() == ModelLoadingState.FULLY_LOADED) {
    return;
  }
synchronized (myLoadLock) {
    if (myIsLoadInProgress) {
      return;
    }
    ModelLoadingState oldState=getLoadingState();
    if (oldState == ModelLoadingState.FULLY_LOADED) {
      return;
    }
    final SModel mi=getSModel();
    try {
      myIsLoadInProgress=true;
      mi.enterUpdateMode();
      YetUnknownResolver yur=new YetUnknownResolver(this,MapSequence.fromMap(myRootsById).values());
      final int RESOLVE_ATTEMPTS_LIMIT=10;
      for (int i=0; i < RESOLVE_ATTEMPTS_LIMIT && yur.collectYetUnresolved(new EmptyProgressMonitor()); i++) {
        yur.replaceYetUnresolved(new EmptyProgressMonitor());
        yur.withImportsOfResolved(new Consumer<SModelReference>(){
          public void accept(          SModelReference mr){
            mi.addModelImport(new SModel.ImportElement(mr));
          }
        }
);
      }
      setLoadingState(ModelLoadingState.FULLY_LOADED);
      fireModelStateChanged(oldState,ModelLoadingState.FULLY_LOADED);
    }
  finally {
      mi.leaveUpdateMode();
      myIsLoadInProgress=false;
    }
  }
}
