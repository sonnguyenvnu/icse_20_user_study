@Override protected HistoryEntry createHistoryEntry(Project project,long historyEntryID) throws Exception {
  Column column=project.columnModel.getColumnByName(_columnName);
  if (column == null) {
    throw new Exception("No column named " + _columnName);
  }
  String description="Remove column " + column.getName();
  Change change=new ColumnRemovalChange(project.columnModel.columns.indexOf(column));
  return new HistoryEntry(historyEntryID,project,description,ColumnRemovalOperation.this,change);
}
