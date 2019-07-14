@Override protected HistoryEntry createHistoryEntry(Project project,long historyEntryID) throws Exception {
  Engine engine=createEngine(project);
  Column column=project.columnModel.getColumnByName(_columnName);
  if (column == null) {
    throw new Exception("No column named " + _columnName);
  }
  List<String> columnNames=new ArrayList<String>();
  List<Integer> rowIndices=new ArrayList<Integer>(project.rows.size());
  List<List<Serializable>> tuples=new ArrayList<List<Serializable>>(project.rows.size());
  FilteredRows filteredRows=engine.getAllFilteredRows();
  RowVisitor rowVisitor;
  if ("lengths".equals(_mode)) {
    rowVisitor=new ColumnSplitRowVisitor(column.getCellIndex(),columnNames,rowIndices,tuples){
      @Override protected java.util.List<Serializable> split(      String s){
        List<Serializable> results=new ArrayList<Serializable>(_fieldLengths.length + 1);
        int lastIndex=0;
        for (        int length : _fieldLengths) {
          int from=lastIndex;
          int to=Math.min(from + length,s.length());
          results.add(stringToValue(s.substring(from,to)));
          lastIndex=to;
        }
        return results;
      }
    }
;
  }
 else   if (_regex) {
    Pattern pattern=Pattern.compile(_separator);
    rowVisitor=new ColumnSplitRowVisitor(column.getCellIndex(),columnNames,rowIndices,tuples){
      @Override protected java.util.List<Serializable> split(      String s){
        return stringArrayToValueList(_pattern.split(s,_maxColumns));
      }
      public RowVisitor init(      Pattern pattern){
        _pattern=pattern;
        return this;
      }
    }
.init(pattern);
  }
 else {
    rowVisitor=new ColumnSplitRowVisitor(column.getCellIndex(),columnNames,rowIndices,tuples){
      @Override protected java.util.List<Serializable> split(      String s){
        return stringArrayToValueList(StringUtils.splitByWholeSeparatorPreserveAllTokens(s,_separator,_maxColumns));
      }
    }
;
  }
  filteredRows.accept(project,rowVisitor);
  String description="Split " + rowIndices.size() + " cell(s) in column " + _columnName + " into several columns" + ("separator".equals(_mode) ? " by separator" : " by field lengths");
  Change change=new ColumnSplitChange(_columnName,columnNames,rowIndices,tuples,_removeOriginalColumn);
  return new HistoryEntry(historyEntryID,project,description,this,change);
}
