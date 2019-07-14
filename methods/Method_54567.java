/** 
 * Unsafe version of  {@link #m_worldOrientation(int) m_worldOrientation}. 
 */
public static double nm_worldOrientation(long struct,int index){
  return UNSAFE.getDouble(null,struct + B3LinkState.M_WORLDORIENTATION + check(index,4) * 8);
}
