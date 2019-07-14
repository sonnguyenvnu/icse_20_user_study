public void remove(){
  clearStatusMessage();
  if (contrib.isInstalled() && contrib instanceof LocalContribution) {
    removeInProgress=true;
    barButtonCardLayout.show(barButtonCardPane,PROGRESS_BAR_CONSTRAINT);
    updateButton.setEnabled(false);
    installRemoveButton.setEnabled(false);
    installProgressBar.setVisible(true);
    installProgressBar.setIndeterminate(true);
    ContribProgressBar monitor=new RemoveProgressBar(installProgressBar);
    getLocalContrib().removeContribution(getBase(),monitor,getStatusPanel());
  }
}
