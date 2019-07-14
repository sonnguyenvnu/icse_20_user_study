/** 
 * Unsafe version of  {@link #m_objectUniqueId(int) m_objectUniqueId}. 
 */
public static void nm_objectUniqueId(long struct,int value){
  UNSAFE.putInt(null,struct + B3CollisionShapeData.M_OBJECTUNIQUEID,value);
}
