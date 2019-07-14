/** 
 * Unsafe version of  {@link #m_collisionGeometryType(int) m_collisionGeometryType}. 
 */
public static void nm_collisionGeometryType(long struct,int value){
  UNSAFE.putInt(null,struct + B3CollisionShapeData.M_COLLISIONGEOMETRYTYPE,value);
}
