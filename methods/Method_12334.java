/** 
 * Always throws  {@link UnsupportedOperationException}. Changing  {@link SurfaceTexture} is notsupported.
 * @param surfaceTexture ignored
 */
@Override public void setSurfaceTexture(SurfaceTexture surfaceTexture){
  throw new UnsupportedOperationException("Changing SurfaceTexture is not supported");
}
