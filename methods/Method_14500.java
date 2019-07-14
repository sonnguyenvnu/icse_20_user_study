protected void generateMaps(){
  _nameToColumn=new HashMap<String,Column>();
  _cellIndexToColumn=new HashMap<Integer,Column>();
  _columnNames=new ArrayList<String>();
  int maxCellIndex=-1;
  for (  Column column : columns) {
    _nameToColumn.put(column.getName(),column);
    int cidx=column.getCellIndex();
    if (cidx > maxCellIndex) {
      maxCellIndex=cidx;
    }
    _cellIndexToColumn.put(cidx,column);
    _columnNames.add(column.getName());
  }
  _maxCellIndex=maxCellIndex;
}
