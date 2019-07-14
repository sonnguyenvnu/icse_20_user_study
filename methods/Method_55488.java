/** 
 * Ensures that the specified FloatBuffer is null-terminated. 
 */
public static void checkNT(FloatBuffer buf){
  checkBuffer(buf.remaining(),1);
  assertNT(buf.get(buf.limit() - 1) == 0.0f);
}
