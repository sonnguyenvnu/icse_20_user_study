public static int utf8Length(final String str){
  int strLen=str.length(), utfLen=0;
  for (int i=0; i != strLen; ++i) {
    char c=str.charAt(i);
    if (c < 0x80) {
      utfLen++;
    }
 else     if (c < 0x800) {
      utfLen+=2;
    }
 else     if (isSurrogate(c)) {
      i++;
      utfLen+=4;
    }
 else {
      utfLen+=3;
    }
  }
  return utfLen;
}
