/** 
 * Unsafe version of  {@link #m_dimensions(int,double) m_dimensions}. 
 */
public static void nm_dimensions(long struct,int index,double value){
  UNSAFE.putDouble(null,struct + B3CollisionShapeData.M_DIMENSIONS + check(index,3) * 8,value);
}
