private void notifyListenersRequirementsStateChange(@Requirements.RequirementFlags int notMetRequirements){
  logdFlags("Not met requirements are changed",notMetRequirements);
  for (  Listener listener : listeners) {
    listener.onRequirementsStateChanged(DownloadManager.this,requirementsWatcher.getRequirements(),notMetRequirements);
  }
}
