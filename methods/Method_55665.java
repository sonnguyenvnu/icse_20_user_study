static int encodeUTF8(CharSequence text,boolean nullTerminated,long target){
  int i=0, len=text.length(), p=0;
  char c;
  while (i < len && (c=text.charAt(i)) < 0x80) {
    UNSAFE.putByte(target + p++,(byte)c);
    i++;
  }
  while (i < len) {
    c=text.charAt(i++);
    if (c < 0x80) {
      UNSAFE.putByte(target + p++,(byte)c);
    }
 else {
      int cp=c;
      if (c < 0x800) {
        UNSAFE.putByte(target + p++,(byte)(0xC0 | cp >> 6));
      }
 else {
        if (!isHighSurrogate(c)) {
          UNSAFE.putByte(target + p++,(byte)(0xE0 | cp >> 12));
        }
 else {
          cp=toCodePoint(c,text.charAt(i++));
          UNSAFE.putByte(target + p++,(byte)(0xF0 | cp >> 18));
          UNSAFE.putByte(target + p++,(byte)(0x80 | cp >> 12 & 0x3F));
        }
        UNSAFE.putByte(target + p++,(byte)(0x80 | cp >> 6 & 0x3F));
      }
      UNSAFE.putByte(target + p++,(byte)(0x80 | cp & 0x3F));
    }
  }
  if (nullTerminated) {
    UNSAFE.putByte(target + p++,(byte)0);
  }
  return p;
}
