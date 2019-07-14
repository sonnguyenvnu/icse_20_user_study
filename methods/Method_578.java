public final String numberString(){
  char chLocal=charAt(np + sp - 1);
  int sp=this.sp;
  if (chLocal == 'L' || chLocal == 'S' || chLocal == 'B' || chLocal == 'F' || chLocal == 'D') {
    sp--;
  }
  return this.subString(np,sp);
}
