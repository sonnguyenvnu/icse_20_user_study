void retrieveOpenDialogDir(JFileChooser fc){
  try {
    String currentDirStr=luytenPrefs.getFileOpenCurrentDirectory();
    if (currentDirStr != null && currentDirStr.trim().length() > 0) {
      File currentDir=new File(currentDirStr);
      if (currentDir.exists() && currentDir.isDirectory()) {
        fc.setCurrentDirectory(currentDir);
      }
    }
  }
 catch (  Exception e) {
    Luyten.showExceptionDialog("Exception!",e);
  }
}
