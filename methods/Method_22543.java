public void update(DetailPanel panel){
  progressPanel.removeAll();
  iconLabel.setIcon(panel.getContrib().isSpecial() ? foundationIcon : null);
  label.setText(panel.description);
  ((HTMLDocument)label.getDocument()).getStyleSheet().addRule(bodyRule);
  updateButton.setEnabled(contributionListing.hasDownloadedLatestList() && (contributionListing.hasUpdates(panel.getContrib()) && !panel.getContrib().isUpdateFlagged()) && !panel.updateInProgress);
  String latestVersion=contributionListing.getLatestPrettyVersion(panel.getContrib());
  String currentVersion=panel.getContrib().getPrettyVersion();
  installButton.setEnabled(!panel.getContrib().isInstalled() && contributionListing.hasDownloadedLatestList() && panel.getContrib().isCompatible(Base.getRevision()) && !panel.installInProgress);
  if (panel.getContrib().isCompatible(Base.getRevision())) {
    if (installButton.isEnabled()) {
      if (latestVersion != null) {
        updateLabel.setText(latestVersion + " available");
      }
 else {
        updateLabel.setText("Available");
      }
    }
 else {
      if (currentVersion != null) {
        updateLabel.setText(currentVersion + " installed");
      }
 else {
        updateLabel.setText("Installed");
      }
    }
  }
 else {
    if (currentVersion != null) {
      updateLabel.setText(currentVersion + " not compatible");
    }
 else {
      updateLabel.setText("Not compatible");
    }
  }
  if (latestVersion != null) {
    latestVersion="Update to " + latestVersion;
  }
 else {
    latestVersion="Update";
  }
  if (updateButton.isEnabled()) {
    updateButton.setText(latestVersion);
  }
 else {
    updateButton.setText("Update");
  }
  removeButton.setEnabled(panel.getContrib().isInstalled() && !panel.removeInProgress);
  progressPanel.add(panel.installProgressBar);
  progressPanel.setVisible(false);
  updateLabel.setVisible(true);
  if (panel.updateInProgress || panel.installInProgress || panel.removeInProgress) {
    progressPanel.setVisible(true);
    updateLabel.setVisible(false);
    progressPanel.repaint();
  }
}
