@Override public void rename(String newName){
  checkRename();
  objectName=newName;
  setModified();
}
