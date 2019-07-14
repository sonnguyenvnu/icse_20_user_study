protected Editor handleOpen(String path,boolean untitled,EditorState state){
  try {
    final File file=new File(path);
    if (!file.exists()) {
      return null;
    }
    for (    Editor editor : editors) {
      for (      SketchCode tab : editor.getSketch().getCode()) {
        if (tab.getFile().equals(file)) {
          editor.toFront();
          Recent.append(editor);
          return editor;
        }
      }
    }
    if (!Sketch.isSanitaryName(file.getName())) {
      Messages.showWarning("You're tricky, but not tricky enough",file.getName() + " is not a valid name for a sketch.\n" + "Better to stick to ASCII, no spaces, and make sure\n" + "it doesn't start with a number.",null);
      return null;
    }
    if (!nextMode.canEdit(file)) {
      final Mode mode=selectMode(file);
      if (mode == null) {
        return null;
      }
      nextMode=mode;
    }
    try {
      Editor editor=nextMode.createEditor(this,path,state);
      editor.setUpdatesAvailable(updatesAvailable);
      editor.getSketch().setUntitled(untitled);
      editors.add(editor);
      Recent.append(editor);
      editor.setVisible(true);
      return editor;
    }
 catch (    EditorException ee) {
      if (ee.getMessage() != null) {
        Messages.showWarning("Error opening sketch",ee.getMessage(),ee);
      }
    }
catch (    NoSuchMethodError nsme) {
      Messages.showWarning("Mode out of date",nextMode.getTitle() + " is not compatible with this version of Processing.\n" + "Try updating the Mode or contact its author for a new version.",nsme);
    }
catch (    Throwable t) {
      if (nextMode.equals(getDefaultMode())) {
        Messages.showTrace("Serious Problem","An unexpected, unknown, and unrecoverable error occurred\n" + "while opening a new editor window. Please report this.",t,true);
      }
 else {
        Messages.showTrace("Mode Problems","A nasty error occurred while trying to use " + nextMode.getTitle() + ".\n" + "It may not be compatible with this version of Processing.\n" + "Try updating the Mode or contact its author for a new version.",t,false);
      }
    }
    if (editors.isEmpty()) {
      Mode defaultMode=getDefaultMode();
      if (nextMode == defaultMode) {
        Messages.showError("Editor Problems","An error occurred while trying to change modes.\n" + "We'll have to quit for now because it's an\n" + "unfortunate bit of indigestion with the default Mode.",null);
      }
 else {
        if (untitled) {
          nextMode=defaultMode;
          handleNew();
          return null;
        }
 else {
          return null;
        }
      }
    }
  }
 catch (  Throwable t) {
    Messages.showTrace("Terrible News","A serious error occurred while " + "trying to create a new editor window.",t,nextMode == getDefaultMode());
    nextMode=getDefaultMode();
  }
  return null;
}
