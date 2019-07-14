private void startCell(String name,Attributes attributes){
  if (ExcelXmlConstants.CELL_TAG.equals(name)) {
    currentCellIndex=attributes.getValue(ExcelXmlConstants.POSITION);
    int nextRow=PositionUtils.getRow(currentCellIndex);
    if (nextRow > curRow) {
      curRow=nextRow;
    }
    analysisContext.setCurrentRowNum(curRow);
    curCol=PositionUtils.getCol(currentCellIndex);
    String cellType=attributes.getValue("t");
    currentCellType=FieldType.EMPTY;
    if (cellType != null && cellType.equals("s")) {
      currentCellType=FieldType.STRING;
    }
  }
}
