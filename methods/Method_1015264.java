public static byte[] concat(byte[] a,byte[] b){
  if (numCodeUnits(a) + numCodeUnits(b) > 255) {
    throw new IllegalArgumentException("cannot create a chunk larger than 255 UTF-16 code units");
  }
  byte[] newChunk=new byte[a.length + b.length - 2];
  arraycopy(a,2,newChunk,2,a.length - 2);
  arraycopy(b,2,newChunk,a.length,b.length - 2);
  newChunk[0]=(byte)(numCodePoints(a) + numCodePoints(b));
  newChunk[1]=(byte)(numCodeUnits(a) + numCodeUnits(b));
  return newChunk;
}
