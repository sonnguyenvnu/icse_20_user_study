public static byte[] slice(byte[] chunk,int start,int end){
  if (start == end) {
    return EMPTY;
  }
 else   if (start == 0 && end == numCodePoints(chunk)) {
    return chunk;
  }
  int startOffset;
  int codeUnits=0;
  int endOffset;
  if (isAscii(chunk)) {
    startOffset=start + 2;
    endOffset=end + 2;
    codeUnits=end - start;
  }
 else {
    startOffset=offset(chunk,start);
    endOffset=startOffset;
    for (int i=start; i < end; i++) {
      byte b=chunk[endOffset];
      int len=b >= 0 ? 1 : prefixLength(b);
      codeUnits+=len == 4 ? 2 : 1;
      endOffset+=len;
    }
  }
  byte[] newChunk=new byte[(endOffset - startOffset) + 2];
  arraycopy(chunk,startOffset,newChunk,2,newChunk.length - 2);
  newChunk[0]=(byte)(end - start);
  newChunk[1]=(byte)codeUnits;
  return newChunk;
}
