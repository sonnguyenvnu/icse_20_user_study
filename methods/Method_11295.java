private int getRowNumber(int row){
  int result=row;
  if (seatChecker == null) {
    return -1;
  }
  for (int i=0; i < row; i++) {
    for (int j=0; j < column; j++) {
      if (seatChecker.isValidSeat(i,j)) {
        break;
      }
      if (j == column - 1) {
        if (i == row) {
          return -1;
        }
        result--;
      }
    }
  }
  return result;
}
