/** 
 * Unsafe version of  {@link #m_textureUniqueId}. 
 */
public static int nm_textureUniqueId(long struct){
  return UNSAFE.getInt(null,struct + B3VisualShapeData.M_TEXTUREUNIQUEID);
}
