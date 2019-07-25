@Override public void flush(){
  ReloadSession session=myReloadSessionBroker.waitForUnemployed();
  if (session == null) {
    return;
  }
  saveAllOpenProjects();
  session.doReload(new EmptyProgressMonitor());
}
