/** 
 * Ensures that the specified IntBuffer is null-terminated. 
 */
public static void checkNT(IntBuffer buf){
  checkBuffer(buf.remaining(),1);
  assertNT(buf.get(buf.limit() - 1) == 0);
}
