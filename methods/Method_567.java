public final String numberString(){
  int offset=np;
  if (offset == -1) {
    offset=0;
  }
  char chLocal=charAt(offset + sp - 1);
  int sp=this.sp;
  if (chLocal == 'L' || chLocal == 'S' || chLocal == 'B' || chLocal == 'F' || chLocal == 'D') {
    sp--;
  }
  String value=new String(buf,offset,sp);
  return value;
}
