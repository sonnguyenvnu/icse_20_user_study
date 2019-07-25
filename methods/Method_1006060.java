@Override public void execute(){
  new VersionWorker(buildInfo.getVersion(),versionPreferences.getIgnoredVersion(),dialogService,taskExecutor).checkForNewVersionAsync(true);
}
