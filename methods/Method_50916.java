private void sortOnColumn(int columnIndex){
  Comparator<Match> comparator=matchColumns[columnIndex].sorter();
  SortingTableModel<Match> model=(SortingTableModel<Match>)resultsTable.getModel();
  if (model.sortColumn() == columnIndex) {
    model.sortDescending(!model.sortDescending());
  }
  model.sortColumn(columnIndex);
  model.sort(comparator);
  resultsTable.getSelectionModel().clearSelection();
  resultsTable.repaint();
}
