public static void getChars(byte b,int index,char[] buf){
  int i=b;
  int q, r;
  int charPos=index;
  char sign=0;
  if (i < 0) {
    sign='-';
    i=-i;
  }
  for (; ; ) {
    q=(i * 52429) >>> (16 + 3);
    r=i - ((q << 3) + (q << 1));
    buf[--charPos]=digits[r];
    i=q;
    if (i == 0)     break;
  }
  if (sign != 0) {
    buf[--charPos]=sign;
  }
}
