/** 
 * Creates instances of TextureRender and SurfaceTexture, and a Surface associated with the SurfaceTexture.
 */
private void setup(){
  mTextureRender=new TextureRender();
  mTextureRender.surfaceCreated();
  if (VERBOSE)   Log.d(TAG,"textureID=" + mTextureRender.getTextureId());
  mSurfaceTexture=new SurfaceTexture(mTextureRender.getTextureId());
  mSurfaceTexture.setOnFrameAvailableListener(this);
  mSurface=new Surface(mSurfaceTexture);
}
