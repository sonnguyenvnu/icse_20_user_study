/** 
 * Unsafe version of  {@link #m_tinyRendererTextureId}. 
 */
public static int nm_tinyRendererTextureId(long struct){
  return UNSAFE.getInt(null,struct + B3VisualShapeData.M_TINYRENDERERTEXTUREID);
}
