public void install(){
  clearStatusMessage();
  installInProgress=true;
  barButtonCardLayout.show(barButtonCardPane,PROGRESS_BAR_CONSTRAINT);
  if (contrib instanceof AvailableContribution) {
    installContribution((AvailableContribution)contrib);
    contribListing.replaceContribution(contrib,contrib);
  }
}
