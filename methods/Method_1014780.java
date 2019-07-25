/** 
 * ????
 */
public void release(){
  if (mStickerTexture == OpenGLUtils.GL_NOT_TEXTURE) {
    mStickerTexture=mRestoreTexture;
  }
  OpenGLUtils.deleteTexture(mStickerTexture);
  mStickerTexture=OpenGLUtils.GL_NOT_TEXTURE;
  mRestoreTexture=OpenGLUtils.GL_NOT_TEXTURE;
  if (mWeakFilter.get() != null) {
    mWeakFilter.clear();
  }
}
