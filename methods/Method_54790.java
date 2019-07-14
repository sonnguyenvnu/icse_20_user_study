/** 
 * Unsafe version of  {@link #m_openglTextureId}. 
 */
public static int nm_openglTextureId(long struct){
  return UNSAFE.getInt(null,struct + B3VisualShapeData.M_OPENGLTEXTUREID);
}
