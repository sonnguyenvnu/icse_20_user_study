@Override public void load(@NotNull Memento memento){
  checkNotRegistered();
  mySourcePathStorage.clearAll();
  if (memento instanceof MementoWithFS) {
    myFileSystem=((MementoWithFS)memento).getFileSystem();
  }
  String path=memento.get(CONTENT_PATH);
  myContentDir=(path != null) ? myFileSystem.getFile(path) : null;
  for (  SourceRootKind kind : getSupportedFileKinds1()) {
    for (    Memento root : memento.getChildren(kind.getName())) {
      String relPath=root.get(LOCATION);
      if (relPath != null) {
        addSourceRoot(kind,new DefaultSourceRoot(relPath,myContentDir));
      }
 else {
        addSourceRoot(kind,new DefaultSourceRoot(root.get(PATH),myContentDir));
      }
    }
  }
}
