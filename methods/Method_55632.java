/** 
 * Polymorphic version of  {@link #memAddress(ByteBuffer)}. 
 */
public static long memAddress(Buffer buffer){
  int elementShift;
  if (buffer instanceof ByteBuffer) {
    elementShift=0;
  }
 else   if (buffer instanceof ShortBuffer || buffer instanceof CharBuffer) {
    elementShift=1;
  }
 else   if (buffer instanceof IntBuffer || buffer instanceof FloatBuffer) {
    elementShift=2;
  }
 else {
    elementShift=3;
  }
  return address(buffer.position(),elementShift,memAddress0(buffer));
}
