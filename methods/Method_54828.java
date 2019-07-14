/** 
 * Unsafe version of  {@link #m_orn(FloatBuffer) m_orn}. 
 */
public static void nm_orn(long struct,FloatBuffer value){
  if (CHECKS) {
    checkGT(value,4);
  }
  memCopy(memAddress(value),struct + B3VRControllerEvent.M_ORN,value.remaining() * 4);
}
