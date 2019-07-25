@Override public boolean move(@NotNull IFile newParent){
  if (newParent instanceof IdeaFile && ((IdeaFile)newParent).findVirtualFile()) {
    try {
      if (findVirtualFile()) {
        assert myVirtualFilePtr != null;
        VirtualFile parentFile=((IdeaFile)newParent).getVirtualFile();
        if (parentFile == null) {
          LOG.error("Could not find the parent file: " + newParent + ". The file was not moved",new Throwable());
          return false;
        }
        myVirtualFilePtr.move(getFileSystem(),parentFile);
        myVirtualFilePtr=findIdeaFile(false);
        return true;
      }
 else {
        LOG.error("Could not find the file to move: " + myPath + ". The file was not moved",new Throwable());
        return false;
      }
    }
 catch (    IOException e) {
      LOG.warn("Could not rename file: ",e);
      return false;
    }
  }
 else {
    return false;
  }
}
