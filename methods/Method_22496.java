protected void resetInstallProgressBarState(){
  installProgressBar.setString(Language.text("contrib.progress.starting"));
  installProgressBar.setIndeterminate(false);
  installProgressBar.setValue(0);
  installProgressBar.setVisible(false);
}
