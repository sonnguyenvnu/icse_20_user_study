public final BigDecimal decimalValue(){
  int offset=np;
  if (offset == -1) {
    offset=0;
  }
  char chLocal=charAt(offset + sp - 1);
  int sp=this.sp;
  if (chLocal == 'L' || chLocal == 'S' || chLocal == 'B' || chLocal == 'F' || chLocal == 'D') {
    sp--;
  }
  return new BigDecimal(buf,offset,sp);
}
