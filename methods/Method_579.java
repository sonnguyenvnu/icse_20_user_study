public final BigDecimal decimalValue(){
  char chLocal=charAt(np + sp - 1);
  int sp=this.sp;
  if (chLocal == 'L' || chLocal == 'S' || chLocal == 'B' || chLocal == 'F' || chLocal == 'D') {
    sp--;
  }
  int offset=np, count=sp;
  if (count < sbuf.length) {
    text.getChars(offset,offset + count,sbuf,0);
    return new BigDecimal(sbuf,0,count);
  }
 else {
    char[] chars=new char[count];
    text.getChars(offset,offset + count,chars,0);
    return new BigDecimal(chars);
  }
}
