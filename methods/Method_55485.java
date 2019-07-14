/** 
 * Ensures that the specified ByteBuffer is null-terminated (last 2 bytes equal to 0). 
 */
public static void checkNT2(ByteBuffer buf){
  checkBuffer(buf.remaining(),2);
  assertNT(buf.get(buf.limit() - 2) == 0);
}
