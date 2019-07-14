public File doSaveAllDialog(String recommendedFileName){
  File selectedFile=null;
  initSaveAllDialog();
  dirPreferences.retrieveSaveDialogDir(fcSaveAll);
  fcSaveAll.setSelectedFile(new File(recommendedFileName));
  int returnVal=fcSaveAll.showSaveDialog(parent);
  dirPreferences.saveSaveDialogDir(fcSaveAll);
  if (returnVal == JFileChooser.APPROVE_OPTION) {
    selectedFile=fcSaveAll.getSelectedFile();
  }
  return selectedFile;
}
