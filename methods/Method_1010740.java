@Override public void synchronize(){
  for (  EditorCell cell : getEditorCells()) {
    ((SynchronizeableEditorCell)cell).synchronize();
  }
  if (hasFoldedCell()) {
    ((SynchronizeableEditorCell)getFoldedCell()).synchronize();
  }
}
