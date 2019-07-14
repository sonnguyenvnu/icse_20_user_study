public static char[] toRawCharArray(final byte[] barr){
  int carrLen=barr.length >> 1;
  if (carrLen << 1 < barr.length) {
    carrLen++;
  }
  char[] carr=new char[carrLen];
  int i=0, j=0;
  while (i < barr.length) {
    char c=(char)(barr[i] << 8);
    i++;
    if (i != barr.length) {
      c+=barr[i] & 0xFF;
      i++;
    }
    carr[j++]=c;
  }
  return carr;
}
