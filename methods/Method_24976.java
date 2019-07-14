/** 
 * Lock the inspector window. Cancels open edits.
 */
public void lock(){
  if (tree.getCellEditor() != null) {
    tree.getCellEditor().cancelCellEditing();
  }
  tree.setEnabled(false);
}
