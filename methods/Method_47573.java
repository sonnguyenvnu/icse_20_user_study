private int calculateNumRows(){
  int offset=findDayOffset();
  int dividend=(offset + mNumCells) / mNumDays;
  int remainder=(offset + mNumCells) % mNumDays;
  return (dividend + (remainder > 0 ? 1 : 0));
}
