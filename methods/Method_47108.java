private void updateBottomBar(){
  String path=!isRootRelativePath() ? compressedFile.getName() + SEPARATOR + relativeDirectory : compressedFile.getName();
  mainActivity.getAppbar().getBottomBar().updatePath(path,false,null,OpenMode.FILE,folder,file,this);
}
