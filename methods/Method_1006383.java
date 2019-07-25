/** 
 * Checks whether the container contains the value x.
 * @param buf underlying buffer
 * @param position starting position of the container in the ByteBuffer
 * @param x target value x
 * @param cardinality container cardinality
 * @return whether the container contains the value x
 */
public static boolean contains(ByteBuffer buf,int position,final short x,int cardinality){
  return BufferUtil.unsignedBinarySearch(buf,position,0,cardinality,x) >= 0;
}
