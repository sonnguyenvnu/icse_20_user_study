@Override public void modify(String oldpath,String oldname,String newPath,String newname){
  utilsHandler.renameBookmark(oldname,oldpath,newname,newPath);
  drawer.refreshDrawer();
}
