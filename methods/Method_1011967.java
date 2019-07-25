@Override public void refresh(@NotNull CachingContext context){
  if (findVirtualFile()) {
    assert myVirtualFilePtr != null;
    myVirtualFilePtr.getChildren();
    myVirtualFilePtr.refresh(!context.isSynchronous(),context.isRecursive());
  }
 else {
    findVirtualFile(true);
  }
}
