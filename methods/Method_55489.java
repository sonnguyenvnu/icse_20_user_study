/** 
 * Ensures that the specified PointerBuffer is null-terminated. 
 */
public static void checkNT(PointerBuffer buf){
  checkBuffer(buf.remaining(),1);
  assertNT(buf.get(buf.limit() - 1) == NULL);
}
