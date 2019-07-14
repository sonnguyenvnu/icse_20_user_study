/** 
 * Handler for the Rename Code menu option.
 */
public void handleRenameCode(){
  ensureExistence();
  if (currentIndex == 0 && isUntitled()) {
    Messages.showMessage(Language.text("rename.messages.is_untitled"),Language.text("rename.messages.is_untitled.description"));
    return;
  }
  if (isModified()) {
    Messages.showMessage(Language.text("menu.file.save"),Language.text("rename.messages.is_modified"));
    return;
  }
  if (isReadOnly()) {
    Messages.showMessage(Language.text("rename.messages.is_read_only"),Language.text("rename.messages.is_read_only.description"));
    return;
  }
  renamingCode=true;
  String prompt=(currentIndex == 0) ? Language.text("editor.sketch.rename.description") : Language.text("editor.tab.rename.description");
  String oldName=(current.isExtension(mode.getDefaultExtension())) ? current.getPrettyName() : current.getFileName();
  promptForTabName(prompt + ":",oldName);
}
