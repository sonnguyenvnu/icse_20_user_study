private boolean notPrecededByEvenNumberOfBackslashes(){
  int pos=ndx;
  int count=0;
  while (pos > 0 && input[pos - 1] == '\\') {
    count++;
    pos--;
  }
  return count % 2 == 0;
}
