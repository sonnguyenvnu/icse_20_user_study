/** 
 * Creates a GL_TEXTURE_EXTERNAL_OES with default configuration of GL_LINEAR filtering and GL_CLAMP_TO_EDGE wrapping.
 */
@TargetApi(15) public static int createExternalTexture(){
  int[] texId=new int[1];
  GLES20.glGenTextures(1,IntBuffer.wrap(texId));
  GLES20.glBindTexture(GLES11Ext.GL_TEXTURE_EXTERNAL_OES,texId[0]);
  GLES20.glTexParameteri(GLES11Ext.GL_TEXTURE_EXTERNAL_OES,GLES20.GL_TEXTURE_MIN_FILTER,GLES20.GL_LINEAR);
  GLES20.glTexParameteri(GLES11Ext.GL_TEXTURE_EXTERNAL_OES,GLES20.GL_TEXTURE_MAG_FILTER,GLES20.GL_LINEAR);
  GLES20.glTexParameteri(GLES11Ext.GL_TEXTURE_EXTERNAL_OES,GLES20.GL_TEXTURE_WRAP_S,GLES20.GL_CLAMP_TO_EDGE);
  GLES20.glTexParameteri(GLES11Ext.GL_TEXTURE_EXTERNAL_OES,GLES20.GL_TEXTURE_WRAP_T,GLES20.GL_CLAMP_TO_EDGE);
  checkGlError();
  return texId[0];
}
