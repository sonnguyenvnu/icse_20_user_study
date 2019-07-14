/** 
 * Ensures that the specified ByteBuffer is null-terminated (last byte equal to 0). 
 */
public static void checkNT1(ByteBuffer buf){
  checkBuffer(buf.remaining(),1);
  assertNT(buf.get(buf.limit() - 1) == 0);
}
