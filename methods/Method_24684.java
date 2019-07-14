public void handleTweak(){
  autoSave();
  if (sketch.isModified()) {
    Messages.showMessage(Language.text("menu.file.save"),Language.text("tweak_mode.save_before_tweak"));
    return;
  }
  handleLaunch(false,true);
}
