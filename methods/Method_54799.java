/** 
 * Unsafe version of  {@link #m_tinyRendererTextureId(int) m_tinyRendererTextureId}. 
 */
public static void nm_tinyRendererTextureId(long struct,int value){
  UNSAFE.putInt(null,struct + B3VisualShapeData.M_TINYRENDERERTEXTUREID,value);
}
