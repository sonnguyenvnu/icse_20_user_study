private static int encodeUTF8LengthSlow(CharSequence value,int offset,int len){
  int bytes=0;
  for (int i=offset; i < len; i++) {
    char c=value.charAt(i);
    if (c < 0x800) {
      bytes+=(0x7F - c) >>> 31;
    }
 else     if (c < MIN_SURROGATE || MAX_SURROGATE < c) {
      bytes+=2;
    }
 else {
      bytes+=2;
      i++;
    }
  }
  return bytes;
}
