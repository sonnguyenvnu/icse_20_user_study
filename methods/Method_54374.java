/** 
 * Unsafe version of  {@link #m_linkIndex}. 
 */
public static int nm_linkIndex(long struct){
  return UNSAFE.getInt(null,struct + B3CollisionShapeData.M_LINKINDEX);
}
