void saveOpenDialogDir(JFileChooser fc){
  try {
    File currentDir=fc.getCurrentDirectory();
    if (currentDir != null && currentDir.exists() && currentDir.isDirectory()) {
      luytenPrefs.setFileOpenCurrentDirectory(currentDir.getAbsolutePath());
    }
  }
 catch (  Exception e) {
    Luyten.showExceptionDialog("Exception!",e);
  }
}
