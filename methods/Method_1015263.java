public static byte[] from(CharSequence cs,int start,int end){
  if (end - start > 255) {
    throw new IllegalArgumentException("cannot encode a block of more than 255 UTF-16 code units");
  }
  int numBytes=0;
  int numCodePoints=0;
  for (int charIdx=start; charIdx < end; numCodePoints++) {
    char c=cs.charAt(charIdx);
    if (isHighSurrogate(c)) {
      numBytes+=4;
      charIdx+=2;
    }
 else {
      numBytes+=encodedLength(c & 0xFFFF);
      charIdx+=1;
    }
  }
  byte[] chunk=new byte[numBytes + 2];
  chunk[0]=(byte)numCodePoints;
  chunk[1]=(byte)(end - start);
  for (int charIdx=start, offset=2; charIdx < end; ) {
    char c=cs.charAt(charIdx);
    int codePoint;
    if (isHighSurrogate(c)) {
      codePoint=Character.toCodePoint(c,cs.charAt(charIdx + 1));
      charIdx+=2;
    }
 else {
      codePoint=c & 0xFFFF;
      charIdx+=1;
    }
    numBytes+=encodedLength(codePoint);
    offset+=overwrite(chunk,offset,codePoint);
  }
  return chunk;
}
