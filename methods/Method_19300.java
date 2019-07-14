private void clearThreadForChangeSet(){
  if (!ComponentsConfiguration.isDebugModeEnabled && !ComponentsConfiguration.isEndToEndTestRun) {
    return;
  }
  mCurrentChangeSetThreadId.set(-1);
}
