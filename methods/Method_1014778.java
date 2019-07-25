/** 
 * ????
 */
public void release(){
  if (mTextureList != null && mTextureList.length > 0) {
    GLES30.glDeleteTextures(mTextureList.length,mTextureList,0);
    mTextureList=null;
  }
  if (mWeakFilter.get() != null) {
    mWeakFilter.clear();
  }
}
