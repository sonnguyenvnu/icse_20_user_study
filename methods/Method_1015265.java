public static byte[] insert(byte[] a,byte[] b,int idx){
  if (numCodeUnits(a) + numCodeUnits(b) > 255) {
    throw new IllegalArgumentException("cannot create a chunk larger than 255 UTF-16 code units");
  }
  int offset=offset(a,idx);
  byte[] newChunk=new byte[a.length + b.length - 2];
  arraycopy(a,2,newChunk,2,offset - 2);
  arraycopy(b,2,newChunk,offset,b.length - 2);
  arraycopy(a,offset,newChunk,offset + b.length - 2,a.length - offset);
  newChunk[0]=(byte)(numCodePoints(a) + numCodePoints(b));
  newChunk[1]=(byte)(numCodeUnits(a) + numCodeUnits(b));
  return newChunk;
}
