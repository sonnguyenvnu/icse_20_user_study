public File doOpenDialog(){
  File selectedFile=null;
  initOpenDialog();
  dirPreferences.retrieveOpenDialogDir(fcOpen);
  int returnVal=fcOpen.showOpenDialog(parent);
  dirPreferences.saveOpenDialogDir(fcOpen);
  if (returnVal == JFileChooser.APPROVE_OPTION) {
    selectedFile=fcOpen.getSelectedFile();
  }
  return selectedFile;
}
