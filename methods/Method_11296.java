private int getColumnNumber(int row,int column){
  int result=column;
  if (seatChecker == null) {
    return -1;
  }
  for (int i=row; i <= row; i++) {
    for (int j=0; j < column; j++) {
      if (!seatChecker.isValidSeat(i,j)) {
        if (j == column) {
          return -1;
        }
        result--;
      }
    }
  }
  return result;
}
