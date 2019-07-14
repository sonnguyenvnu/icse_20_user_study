/** 
 * Unsafe version of  {@link #m_pos(FloatBuffer) m_pos}. 
 */
public static void nm_pos(long struct,FloatBuffer value){
  if (CHECKS) {
    checkGT(value,4);
  }
  memCopy(memAddress(value),struct + B3VRControllerEvent.M_POS,value.remaining() * 4);
}
