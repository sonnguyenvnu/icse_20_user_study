/** 
 * Checks whether the container contains the value i.
 * @param buf underlying buffer
 * @param position position of the container in the buffer
 * @param i index
 * @return whether the container contains the value i
 */
public static boolean contains(ByteBuffer buf,int position,final short i){
  final int x=toIntUnsigned(i);
  return (buf.getLong(x / 64 * 8 + position) & (1L << x)) != 0;
}
