@Override public void clearVideoTextureView(TextureView textureView){
  verifyApplicationThread();
  if (textureView != null && textureView == this.textureView) {
    setVideoTextureView(null);
  }
}
