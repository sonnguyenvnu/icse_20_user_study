private int getHorizontalBlockNumber(int total,int divider){
  boolean isInteger=total % divider == 0;
  int result=total / divider;
  return (isInteger) ? result : result + 1;
}
