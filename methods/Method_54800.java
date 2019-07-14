/** 
 * Unsafe version of  {@link #m_textureUniqueId(int) m_textureUniqueId}. 
 */
public static void nm_textureUniqueId(long struct,int value){
  UNSAFE.putInt(null,struct + B3VisualShapeData.M_TEXTUREUNIQUEID,value);
}
