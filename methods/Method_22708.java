/** 
 * Remove a piece of code from the sketch and from the disk.
 */
public void handleDeleteCode(){
  ensureExistence();
  if (isReadOnly()) {
    Messages.showMessage(Language.text("delete.messages.is_read_only"),Language.text("delete.messages.is_read_only.description"));
    return;
  }
  if (currentIndex == 0 && isUntitled()) {
    Messages.showMessage(Language.text("delete.messages.cannot_delete"),Language.text("delete.messages.cannot_delete.description"));
    return;
  }
  Object[] options={Language.text("prompt.ok"),Language.text("prompt.cancel")};
  String prompt=(currentIndex == 0) ? Language.text("warn.delete.sketch") : Language.interpolate("warn.delete.file",current.getPrettyName());
  int result=JOptionPane.showOptionDialog(editor,prompt,Language.text("warn.delete"),JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,options,options[0]);
  if (result == JOptionPane.YES_OPTION) {
    if (currentIndex == 0) {
      Util.removeDir(folder);
      editor.getBase().rebuildSketchbookMenus();
      editor.getBase().handleClose(editor,false);
    }
 else {
      if (!current.deleteFile()) {
        Messages.showMessage(Language.text("delete.messages.cannot_delete.file"),Language.text("delete.messages.cannot_delete.file.description") + " \"" + current.getFileName() + "\".");
        return;
      }
      removeCode(current);
      editor.rebuildHeader();
      setCurrentCode(0);
    }
  }
}
