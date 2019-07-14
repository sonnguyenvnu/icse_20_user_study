/** 
 * Unsafe version of  {@link #m_dimensions(int) m_dimensions}. 
 */
public static double nm_dimensions(long struct,int index){
  return UNSAFE.getDouble(null,struct + B3CollisionShapeData.M_DIMENSIONS + check(index,3) * 8);
}
