/** 
 * Checks to see if the sketch has been modified, and if so, asks the user to save the sketch or cancel the export. This prevents issues where an incomplete version of the sketch would be exported, and is a fix for <A HREF="http://dev.processing.org/bugs/show_bug.cgi?id=157">Bug 157</A>
 */
protected boolean handleExportCheckModified(){
  if (sketch.isReadOnly()) {
    Messages.showMessage(Language.text("export.messages.is_read_only"),Language.text("export.messages.is_read_only.description"));
    return false;
  }
  if (sketch.isUntitled()) {
    Messages.showMessage(Language.text("export.messages.cannot_export"),Language.text("export.messages.cannot_export.description"));
    return false;
  }
  if (sketch.isModified()) {
    Object[] options={Language.text("prompt.ok"),Language.text("prompt.cancel")};
    int result=JOptionPane.showOptionDialog(this,Language.text("export.unsaved_changes"),Language.text("menu.file.save"),JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE,null,options,options[0]);
    if (result == JOptionPane.OK_OPTION) {
      handleSave(true);
    }
 else {
      statusNotice(Language.text("export.notice.cancel.unsaved_changes"));
      return false;
    }
  }
  return true;
}
