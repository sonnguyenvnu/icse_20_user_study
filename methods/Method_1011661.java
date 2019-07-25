public int dataidx(int row,int col){
  return (row - 1) * (getColumnCount() - 1) + (col - 1);
}
