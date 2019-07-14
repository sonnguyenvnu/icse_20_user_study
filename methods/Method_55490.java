/** 
 * Ensures that the specified PointerBuffer is terminated with the specified terminator. 
 */
public static void checkNT(PointerBuffer buf,long terminator){
  checkBuffer(buf.remaining(),1);
  assertNT(buf.get(buf.limit() - 1) == terminator);
}
