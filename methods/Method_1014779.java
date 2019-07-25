/** 
 * ??????????
 */
public void reset(){
  if (mMaskTexture != OpenGLUtils.GL_NOT_TEXTURE) {
    GLES30.glDeleteTextures(1,new int[]{mMaskTexture},0);
    mMaskTexture=OpenGLUtils.GL_NOT_TEXTURE;
  }
  mEnableRender=false;
}
