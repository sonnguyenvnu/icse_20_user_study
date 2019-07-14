/** 
 * Switch between tabs, this swaps out the Document object that's currently being manipulated.
 */
public void setCode(SketchCode code){
  SyntaxDocument document=(SyntaxDocument)code.getDocument();
  if (document == null) {
    document=new SyntaxDocument(){
      @Override public void beginCompoundEdit(){
        if (compoundEdit == null)         startCompoundEdit();
        super.beginCompoundEdit();
      }
      @Override public void endCompoundEdit(){
        stopCompoundEdit();
        super.endCompoundEdit();
      }
    }
;
    code.setDocument(document);
    document.setTokenMarker(mode.getTokenMarker(code));
    try {
      document.insertString(0,code.getProgram(),null);
    }
 catch (    BadLocationException bl) {
      bl.printStackTrace();
    }
    document.addDocumentListener(new DocumentListener(){
      public void removeUpdate(      DocumentEvent e){
        if (isInserting && isDirectEdit() && !textarea.isOverwriteEnabled()) {
          endTextEditHistory();
        }
        isInserting=false;
      }
      public void insertUpdate(      DocumentEvent e){
        if (!isInserting && !textarea.isOverwriteEnabled() && isDirectEdit()) {
          endTextEditHistory();
        }
        if (!textarea.isOverwriteEnabled()) {
          isInserting=true;
        }
      }
      public void changedUpdate(      DocumentEvent e){
        endTextEditHistory();
      }
    }
);
    document.addUndoableEditListener(new UndoableEditListener(){
      public void undoableEditHappened(      UndoableEditEvent e){
        if (endUndoEvent != null) {
          endUndoEvent.cancel();
          endUndoEvent=null;
          startTimerEvent();
        }
        if (compoundEdit == null) {
          startCompoundEdit();
          startTimerEvent();
        }
        compoundEdit.addEdit(e.getEdit());
        undoAction.updateUndoState();
        redoAction.updateRedoState();
      }
    }
);
  }
  textarea.setDocument(document,code.getSelectionStart(),code.getSelectionStop(),code.getScrollPosition());
  textarea.requestFocusInWindow();
  this.undo=code.getUndo();
  undoAction.updateUndoState();
  redoAction.updateRedoState();
}
