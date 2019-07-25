public synchronized void load(){
  FileTime latestModifiedTime=IOHelper.getLastModifiedTime(getPath());
  setLastModifiedTime(latestModifiedTime);
  try {
    String content=IOHelper.readFile(getPath());
    editorPane.setEditorValue(content);
    this.select();
    setTabText(getPath().getFileName().toString());
    setChangedProperty(false);
  }
 catch (  Exception e) {
    closeIt();
  }
}
