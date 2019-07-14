/** 
 * Unsafe version of  {@link #m_pos(int) m_pos}. 
 */
public static float nm_pos(long struct,int index){
  return UNSAFE.getFloat(null,struct + B3VRControllerEvent.M_POS + check(index,4) * 4);
}
