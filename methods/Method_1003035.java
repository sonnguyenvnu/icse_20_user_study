@Override public int update(){
  if (dropAllObjects) {
    dropAllObjects();
  }
  if (deleteFiles) {
    session.getDatabase().setDeleteFilesOnDisconnect(true);
  }
  return 0;
}
