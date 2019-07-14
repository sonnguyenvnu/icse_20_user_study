/** 
 * Handler for the New Code menu option.
 */
public void handleNewCode(){
  ensureExistence();
  if (isReadOnly()) {
    Messages.showMessage(Language.text("new.messages.is_read_only"),Language.text("new.messages.is_read_only.description"));
    return;
  }
  renamingCode=false;
  promptForTabName(Language.text("editor.tab.rename.description") + ":","");
}
