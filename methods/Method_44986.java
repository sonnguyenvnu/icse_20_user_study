public File doSaveDialog(String recommendedFileName){
  File selectedFile=null;
  initSaveDialog();
  dirPreferences.retrieveSaveDialogDir(fcSave);
  fcSave.setSelectedFile(new File(recommendedFileName));
  int returnVal=fcSave.showSaveDialog(parent);
  dirPreferences.saveSaveDialogDir(fcSave);
  if (returnVal == JFileChooser.APPROVE_OPTION) {
    selectedFile=fcSave.getSelectedFile();
  }
  return selectedFile;
}
