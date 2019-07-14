static public void showChanges(){
  if (!Base.isCommandLine()) {
    Platform.openURL("https://github.com/processing/processing/wiki/Changes");
  }
}
