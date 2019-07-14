/** 
 * Unsafe version of  {@link #m_localCollisionFrame}. 
 */
public static DoubleBuffer nm_localCollisionFrame(long struct){
  return memDoubleBuffer(struct + B3CollisionShapeData.M_LOCALCOLLISIONFRAME,7);
}
