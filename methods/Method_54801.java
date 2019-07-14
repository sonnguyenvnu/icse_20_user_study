/** 
 * Unsafe version of  {@link #m_openglTextureId(int) m_openglTextureId}. 
 */
public static void nm_openglTextureId(long struct,int value){
  UNSAFE.putInt(null,struct + B3VisualShapeData.M_OPENGLTEXTUREID,value);
}
