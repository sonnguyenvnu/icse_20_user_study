private void setProgressControls(boolean isRunning){
  progressPanel.setVisible(isRunning);
  goButton.setEnabled(!isRunning);
  cancelButton.setEnabled(isRunning);
}
