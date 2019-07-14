void retrieveSaveDialogDir(JFileChooser fc){
  try {
    String currentDirStr=luytenPrefs.getFileSaveCurrentDirectory();
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
