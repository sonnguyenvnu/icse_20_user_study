/** 
 * Converts segment of byte array into long array.
 */
protected static long[] bytesToLongs(final byte[] ba,final int offset,final int size){
  long[] result=new long[size >> 3];
  int i8=offset;
  for (int i=0; i < result.length; i++) {
    result[i]=Bits.getLong(ba,i8);
    i8+=8;
  }
  return result;
}
