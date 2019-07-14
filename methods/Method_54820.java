/** 
 * Unsafe version of  {@link #m_orn(int) m_orn}. 
 */
public static float nm_orn(long struct,int index){
  return UNSAFE.getFloat(null,struct + B3VRControllerEvent.M_ORN + check(index,4) * 4);
}
