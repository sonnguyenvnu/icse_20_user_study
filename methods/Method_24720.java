protected void stopTweakMode(List<List<Handle>> handles){
  tweakClient.shutdown();
  getJavaTextArea().stopTweakMode();
  boolean[] tweakedTabs=getTweakedTabs(handles);
  boolean modified=anythingTrue(tweakedTabs);
  if (modified) {
    if (Messages.showYesNoQuestion(this,Language.text("tweak_mode"),Language.text("tweak_mode.keep_changes.line1"),Language.text("tweak_mode.keep_changes.line2")) == JOptionPane.YES_OPTION) {
      for (int i=0; i < sketch.getCodeCount(); i++) {
        if (tweakedTabs[i]) {
          sketch.getCode(i).setModified(true);
        }
 else {
          sketch.getCode(i).setProgram(sketch.getCode(i).getSavedProgram());
          sketch.getCode(i).setDocument(null);
          if (i == sketch.getCurrentCodeIndex()) {
            setCode(sketch.getCurrentCode());
          }
        }
      }
      try {
        sketch.save();
      }
 catch (      IOException e) {
        Messages.showWarning("Error","Could not save the modified sketch.",e);
      }
      header.repaint();
      textarea.invalidate();
    }
 else {
      loadSavedCode();
      textarea.invalidate();
    }
  }
 else {
    loadSavedCode();
    textarea.invalidate();
  }
}
