@Override protected HistoryEntry createHistoryEntry(Project project,long historyEntryID) throws Exception {
  Column column=project.columnModel.getColumnByName(_columnName);
  if (column == null) {
    throw new Exception("No column named " + _columnName);
  }
  int cellIndex=column.getCellIndex();
  Column keyColumn=project.columnModel.getColumnByName(_keyColumnName);
  if (keyColumn == null) {
    throw new Exception("No key column named " + _keyColumnName);
  }
  int keyCellIndex=keyColumn.getCellIndex();
  List<Row> newRows=new ArrayList<Row>();
  int oldRowCount=project.rows.size();
  for (int r=0; r < oldRowCount; r++) {
    Row oldRow=project.rows.get(r);
    if (oldRow.isCellBlank(keyCellIndex)) {
      newRows.add(oldRow.dup());
      continue;
    }
    int r2=r + 1;
    while (r2 < oldRowCount && project.rows.get(r2).isCellBlank(keyCellIndex)) {
      r2++;
    }
    if (r2 == r + 1) {
      newRows.add(oldRow.dup());
      continue;
    }
    StringBuffer sb=new StringBuffer();
    for (int r3=r; r3 < r2; r3++) {
      Object value=project.rows.get(r3).getCellValue(cellIndex);
      if (ExpressionUtils.isNonBlankData(value)) {
        if (sb.length() > 0) {
          sb.append(_separator);
        }
        sb.append(value.toString());
      }
    }
    for (int r3=r; r3 < r2; r3++) {
      Row newRow=project.rows.get(r3).dup();
      if (r3 == r) {
        newRow.setCell(cellIndex,new Cell(sb.toString(),null));
      }
 else {
        newRow.setCell(cellIndex,null);
      }
      if (!newRow.isEmpty()) {
        newRows.add(newRow);
      }
    }
    r=r2 - 1;
  }
  return new HistoryEntry(historyEntryID,project,getBriefDescription(null),this,new MassRowChange(newRows));
}
