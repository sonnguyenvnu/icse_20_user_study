public void reset(DebugConnectionSettings settings){
  myHost=settings.getHostName();
  myPort=settings.getPort();
  myCommandLine=formClientCommandLine();
  updateUiFromFields();
}
