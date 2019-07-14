void saveSaveDialogDir(JFileChooser fc){
  try {
    File currentDir=fc.getCurrentDirectory();
    if (currentDir != null && currentDir.exists() && currentDir.isDirectory()) {
      luytenPrefs.setFileSaveCurrentDirectory(currentDir.getAbsolutePath());
    }
  }
 catch (  Exception e) {
    Luyten.showExceptionDialog("Exception!",e);
  }
}
