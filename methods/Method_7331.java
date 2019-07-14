private void setup(){
  mTextureRender=new TextureRenderer(rotateRender);
  mTextureRender.surfaceCreated();
  mSurfaceTexture=new SurfaceTexture(mTextureRender.getTextureId());
  mSurfaceTexture.setOnFrameAvailableListener(this);
  mSurface=new Surface(mSurfaceTexture);
}
