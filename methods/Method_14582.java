@Override protected HistoryEntry createHistoryEntry(Project project,long historyEntryID) throws Exception {
  if (project.columnModel.getColumnByName(_columnName) == null) {
    throw new Exception("No column named " + _columnName);
  }
  if (_index < 0 || _index >= project.columnModel.columns.size()) {
    throw new Exception("New column index out of range " + _index);
  }
  Change change=new ColumnMoveChange(_columnName,_index);
  return new HistoryEntry(historyEntryID,project,getBriefDescription(null),ColumnMoveOperation.this,change);
}
