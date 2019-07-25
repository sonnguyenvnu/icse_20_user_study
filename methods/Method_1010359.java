public void dispose(){
  checkNotDisposed();
  for (  SModel sm : new ArrayList<>(myModelsToListen)) {
    stopListeningToModel(sm);
  }
  myModelAccess.removeCommandListener(myCommandListener);
  myDisposed=true;
}
