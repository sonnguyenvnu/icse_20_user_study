boolean checkValid(Integer[] columns,int row1,int column1){
  for (int row2=0; row2 < row1; row2++) {
    int column2=columns[row2];
    if (column1 == column2) {
      return false;
    }
    int columnDistance=Math.abs(column2 - column1);
    int rowDistance=row1 - row2;
    if (columnDistance == rowDistance) {
      return false;
    }
  }
  return true;
}
