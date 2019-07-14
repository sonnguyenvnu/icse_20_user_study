private void setListDataFrom(List<Match> matches){
  resultsTable.setModel(tableModelFrom(matches));
  TableColumnModel colModel=resultsTable.getColumnModel();
  TableColumn column;
  int width;
  for (int i=0; i < matchColumns.length; i++) {
    if (matchColumns[i].width() > 0) {
      column=colModel.getColumn(i);
      width=matchColumns[i].width();
      column.setPreferredWidth(width);
      column.setMinWidth(width);
      column.setMaxWidth(width);
    }
  }
}
