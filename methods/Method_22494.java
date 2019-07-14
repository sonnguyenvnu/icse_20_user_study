private void finishInstall(boolean error){
  resetInstallProgressBarState();
  installRemoveButton.setEnabled(!contrib.isUpdateFlagged());
  if (error) {
    setErrorMessage(Language.text("contrib.download_error"));
  }
  barButtonCardLayout.show(barButtonCardPane,BUTTON_CONSTRAINT);
  installInProgress=false;
  if (updateInProgress) {
    updateInProgress=false;
  }
  updateButton.setVisible(contribListing.hasUpdates(contrib) && !contrib.isUpdateFlagged());
  setSelected(true);
}
