private void createEditorNode(){
  EventHandler<KeyEvent> keyEventsHandler=t -> {
    if (t.getCode() == KeyCode.ENTER) {
      commitHelper(false);
    }
 else     if (t.getCode() == KeyCode.ESCAPE) {
      cancelEdit();
    }
 else     if (t.getCode() == KeyCode.TAB) {
      commitHelper(false);
      editNext(!t.isShiftDown());
    }
  }
;
  ChangeListener<Boolean> focusChangeListener=(observable,oldValue,newValue) -> {
    if (editorNode != null && !newValue) {
      commitHelper(true);
    }
  }
;
  editorNode=builder.createNode(getValue(),keyEventsHandler,focusChangeListener);
}
