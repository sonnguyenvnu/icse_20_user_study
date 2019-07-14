public int getMapperPosition(int rawPosition){
  int cellCount=cells == null ? 0 : cells.size();
  if (cellCount == 0) {
    return rawPosition;
  }
  int columnCount=(int)(cellCount * 1.0f / maxRows + 0.5f);
  int rowIndex=rawPosition / maxRows;
  int columnIndex=rawPosition % maxRows;
  return columnIndex * columnCount + rowIndex;
}
