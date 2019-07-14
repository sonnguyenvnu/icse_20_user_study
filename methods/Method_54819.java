/** 
 * Unsafe version of  {@link #m_orn}. 
 */
public static FloatBuffer nm_orn(long struct){
  return memFloatBuffer(struct + B3VRControllerEvent.M_ORN,4);
}
