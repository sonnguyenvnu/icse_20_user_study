/** 
 * Unsafe version of  {@link #m_worldOrientation(int,double) m_worldOrientation}. 
 */
public static void nm_worldOrientation(long struct,int index,double value){
  UNSAFE.putDouble(null,struct + B3LinkState.M_WORLDORIENTATION + check(index,4) * 8,value);
}
