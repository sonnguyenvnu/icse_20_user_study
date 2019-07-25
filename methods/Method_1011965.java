@Override public boolean mkdirs(){
  ApplicationManager.getApplication().assertWriteAccessAllowed();
  if (findVirtualFile()) {
    assert myVirtualFilePtr != null;
    return myVirtualFilePtr.isDirectory();
  }
 else {
    try {
      myVirtualFilePtr=createDirectories(myPath);
      return true;
    }
 catch (    IOException ex) {
      return false;
    }
  }
}
