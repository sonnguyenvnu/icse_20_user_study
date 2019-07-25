@Override public void destroy(boolean releaseTexture){
  if (releaseTexture) {
    mVisualSurface.release();
  }
}
