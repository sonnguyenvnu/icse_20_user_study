@Override protected HistoryEntry createHistoryEntry(Project project,long historyEntryID) throws Exception {
  int keyColumnIndex=project.columnModel.getColumnIndexByName(_keyColumnName);
  int valueColumnIndex=project.columnModel.getColumnIndexByName(_valueColumnName);
  int noteColumnIndex=_noteColumnName == null ? -1 : project.columnModel.getColumnIndexByName(_noteColumnName);
  Column keyColumn=project.columnModel.getColumnByName(_keyColumnName);
  Column valueColumn=project.columnModel.getColumnByName(_valueColumnName);
  Column noteColumn=_noteColumnName == null ? null : project.columnModel.getColumnByName(_noteColumnName);
  List<Column> unchangedColumns=new ArrayList<Column>();
  List<Column> oldColumns=project.columnModel.columns;
  for (int i=0; i < oldColumns.size(); i++) {
    if (i != keyColumnIndex && i != valueColumnIndex && i != noteColumnIndex) {
      unchangedColumns.add(oldColumns.get(i));
    }
  }
  List<Column> newColumns=new ArrayList<Column>();
  List<Column> newNoteColumns=new ArrayList<Column>();
  Map<String,Column> keyValueToColumn=new HashMap<String,Column>();
  Map<String,Column> keyValueToNoteColumn=new HashMap<String,Column>();
  Map<String,Row> groupByCellValuesToRow=new HashMap<String,Row>();
  List<Row> newRows=new ArrayList<Row>();
  List<Row> oldRows=project.rows;
  Row reusableRow=null;
  List<Row> currentRows=new ArrayList<Row>();
  String recordKey=null;
  if (unchangedColumns.isEmpty()) {
    reusableRow=new Row(1);
    newRows.add(reusableRow);
    currentRows.clear();
    currentRows.add(reusableRow);
  }
  for (int r=0; r < oldRows.size(); r++) {
    Row oldRow=oldRows.get(r);
    Object key=oldRow.getCellValue(keyColumn.getCellIndex());
    if (!ExpressionUtils.isNonBlankData(key)) {
      if (unchangedColumns.isEmpty()) {
        reusableRow=new Row(newColumns.size());
        newRows.add(reusableRow);
        currentRows.clear();
        currentRows.add(reusableRow);
      }
 else {
        newRows.add(buildNewRow(unchangedColumns,oldRow,unchangedColumns.size()));
      }
      continue;
    }
    String keyString=key.toString();
    if (keyString.equals(recordKey) || recordKey == null) {
      reusableRow=new Row(newColumns.size());
      newRows.add(reusableRow);
      currentRows.clear();
      currentRows.add(reusableRow);
    }
    Column newColumn=keyValueToColumn.get(keyString);
    if (newColumn == null) {
      newColumn=new Column(project.columnModel.allocateNewCellIndex(),project.columnModel.getUnduplicatedColumnName(keyString));
      keyValueToColumn.put(keyString,newColumn);
      newColumns.add(newColumn);
      if (recordKey == null) {
        recordKey=keyString;
      }
    }
    if (unchangedColumns.size() > 0) {
      StringBuffer sb=new StringBuffer();
      for (int c=0; c < unchangedColumns.size(); c++) {
        Column unchangedColumn=unchangedColumns.get(c);
        Object cellValue=oldRow.getCellValue(unchangedColumn.getCellIndex());
        if (c > 0) {
          sb.append('\0');
        }
        if (cellValue != null) {
          sb.append(cellValue.toString());
        }
      }
      String unchangedCellValues=sb.toString();
      reusableRow=groupByCellValuesToRow.get(unchangedCellValues);
      if (reusableRow == null || reusableRow.getCellValue(valueColumn.getCellIndex()) != null) {
        reusableRow=buildNewRow(unchangedColumns,oldRow,newColumn.getCellIndex() + 1);
        groupByCellValuesToRow.put(unchangedCellValues,reusableRow);
        newRows.add(reusableRow);
      }
    }
    Cell cell=oldRow.getCell(valueColumn.getCellIndex());
    if (unchangedColumns.size() == 0) {
      int index=newColumn.getCellIndex();
      Row row=getAvailableRow(currentRows,newRows,index);
      row.setCell(index,cell);
    }
 else {
      reusableRow.setCell(newColumn.getCellIndex(),cell);
    }
    if (noteColumn != null) {
      Object noteValue=oldRow.getCellValue(noteColumn.getCellIndex());
      if (ExpressionUtils.isNonBlankData(noteValue)) {
        Column newNoteColumn=keyValueToNoteColumn.get(keyString);
        if (newNoteColumn == null) {
          newNoteColumn=new Column(project.columnModel.allocateNewCellIndex(),project.columnModel.getUnduplicatedColumnName(noteColumn.getName() + " : " + keyString));
          keyValueToNoteColumn.put(keyString,newNoteColumn);
          newNoteColumns.add(newNoteColumn);
        }
        int newNoteCellIndex=newNoteColumn.getCellIndex();
        Object existingNewNoteValue=reusableRow.getCellValue(newNoteCellIndex);
        if (ExpressionUtils.isNonBlankData(existingNewNoteValue)) {
          Cell concatenatedNoteCell=new Cell(existingNewNoteValue.toString() + ";" + noteValue.toString(),null);
          reusableRow.setCell(newNoteCellIndex,concatenatedNoteCell);
        }
 else {
          reusableRow.setCell(newNoteCellIndex,oldRow.getCell(noteColumn.getCellIndex()));
        }
      }
    }
  }
  List<Column> allColumns=new ArrayList<Column>(unchangedColumns);
  allColumns.addAll(newColumns);
  allColumns.addAll(newNoteColumns);
  for (int i=newRows.size() - 1; i >= 0; i--) {
    if (newRows.get(i).isEmpty())     newRows.remove(i);
  }
  return new HistoryEntry(historyEntryID,project,getBriefDescription(null),this,new MassRowColumnChange(allColumns,newRows));
}
