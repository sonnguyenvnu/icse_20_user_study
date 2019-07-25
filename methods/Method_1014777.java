/** 
 * Recreate the EGLSurface, using the new EglBase.  The caller should have already freed the old EGLSurface with releaseEglSurface(). <p> This is useful when we want to update the EGLSurface associated with a Surface. For example, if we want to share with a different EGLContext, which can only be done by tearing down and recreating the context.  (That's handled by the caller; this just creates a new EGLSurface for the Surface we were handed earlier.) <p> If the previous EGLSurface isn't fully destroyed, e.g. it's still current on a context somewhere, the create call will fail with complaints from the Surface about already being connected.
 */
public void recreate(EglCore newEglCore){
  if (mSurface == null) {
    throw new RuntimeException("not yet implemented for SurfaceTexture");
  }
  mEglCore=newEglCore;
  createWindowSurface(mSurface);
}
