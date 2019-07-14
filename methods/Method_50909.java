private void deleteMatchlistSelections(){
  int[] selectionIndices=resultsTable.getSelectedRows();
  for (int i=selectionIndices.length - 1; i >= 0; i--) {
    matches.remove(selectionIndices[i]);
  }
  resultsTable.getSelectionModel().clearSelection();
  resultsTable.addNotify();
}
