/** 
 * Ensures that the specified IntBuffer is terminated with the specified terminator. 
 */
public static void checkNT(IntBuffer buf,int terminator){
  checkBuffer(buf.remaining(),1);
  assertNT(buf.get(buf.limit() - 1) == terminator);
}
