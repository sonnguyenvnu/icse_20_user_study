/** 
 * Use with startCompoundEdit() to group edit operations in a single undo.
 */
public void stopCompoundEdit(){
  if (compoundEdit != null) {
    compoundEdit.end();
    undo.addEdit(compoundEdit);
    undoAction.updateUndoState();
    redoAction.updateRedoState();
    compoundEdit=null;
  }
}
