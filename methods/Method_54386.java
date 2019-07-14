/** 
 * Unsafe version of  {@link #m_numCollisionShapes}. 
 */
public static int nm_numCollisionShapes(long struct){
  return UNSAFE.getInt(null,struct + B3CollisionShapeInformation.M_NUMCOLLISIONSHAPES);
}
