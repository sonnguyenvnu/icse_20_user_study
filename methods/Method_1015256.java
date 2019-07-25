/** 
 * Returns a copy of the vector, with a bit range excised from the specified location.
 * @param vector    the bit vector
 * @param vectorLen the length of the bit vector
 * @param offset    the offset within the bit vector
 * @param len       the length of the excised bit range
 * @return an updated copy of the vector
 */
public static long[] remove(long[] vector,int vectorLen,int offset,int len){
  long[] updated=create(vectorLen - len);
  int idx=offset >> 6;
  System.arraycopy(vector,0,updated,0,idx);
  if (idx < updated.length) {
    int delta=offset & 63;
    updated[idx]|=vector[idx] & maskBelow(delta);
  }
  copy(vector,offset + len,updated,offset,vectorLen - (offset + len));
  return updated;
}
