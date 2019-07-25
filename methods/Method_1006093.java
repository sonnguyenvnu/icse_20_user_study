@Override public void execute(){
  BibDatabase database=stateManager.getActiveDatabase().orElseThrow(() -> new NullPointerException("Database null")).getDatabase();
  TextArea editor=new TextArea();
  editor.setText(database.getPreamble().orElse(""));
  DialogPane pane=new DialogPane();
  pane.setContent(editor);
  Optional<ButtonType> pressedButton=dialogService.showCustomDialogAndWait(Localization.lang("Edit Preamble"),pane,ButtonType.APPLY,ButtonType.CANCEL);
  if (pressedButton.isPresent() && pressedButton.get().equals(ButtonType.APPLY)) {
    String newPreamble=editor.getText();
    if (!database.getPreamble().orElse("").equals(newPreamble)) {
      undoManager.addEdit(new UndoablePreambleChange(database,database.getPreamble().orElse(null),newPreamble));
      database.setPreamble(newPreamble);
    }
  }
}
