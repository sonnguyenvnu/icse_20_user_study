public int getRowsCount(int width){
  if (rowsCount == 0) {
    prepareLayout(width);
  }
  return rowsCount;
}
