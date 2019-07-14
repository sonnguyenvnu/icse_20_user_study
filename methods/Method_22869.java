@Override public void windowGainedFocus(WindowEvent e){
  if (Preferences.getBoolean("editor.watcher")) {
    if (sketch != null) {
      sketch.ensureExistence();
      checkFiles();
    }
  }
}
