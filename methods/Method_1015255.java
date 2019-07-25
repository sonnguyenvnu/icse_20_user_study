/** 
 * Returns a copy of the vector, with an empty bit range inserted at the specified location.
 * @param vector    the bit vector
 * @param vectorLen the length of the bit vector
 * @param offset    the offset within the bit vector
 * @param len       the length of the empty bit range
 * @return an updated copy of the vector
 */
public static long[] interpose(long[] vector,int vectorLen,int offset,int len){
  long[] updated=create(vectorLen + len);
  int idx=offset >> 6;
  System.arraycopy(vector,0,updated,0,idx);
  if (idx < vector.length) {
    int delta=offset & 63;
    updated[idx]|=vector[idx] & maskBelow(delta);
  }
  copy(vector,offset,updated,offset + len,vectorLen - offset);
  return updated;
}
