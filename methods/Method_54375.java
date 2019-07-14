/** 
 * Unsafe version of  {@link #m_collisionGeometryType}. 
 */
public static int nm_collisionGeometryType(long struct){
  return UNSAFE.getInt(null,struct + B3CollisionShapeData.M_COLLISIONGEOMETRYTYPE);
}
