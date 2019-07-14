/** 
 * Returns the wrapped  {@link SurfaceTexture}. This can only be called after  {@link #init(int)}.
 */
public SurfaceTexture getSurfaceTexture(){
  return Assertions.checkNotNull(texture);
}
