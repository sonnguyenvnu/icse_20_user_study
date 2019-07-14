private void update(RectF bounds,Runnable action){
  GLES20.glBindFramebuffer(GLES20.GL_FRAMEBUFFER,getReusableFramebuffer());
  GLES20.glFramebufferTexture2D(GLES20.GL_FRAMEBUFFER,GLES20.GL_COLOR_ATTACHMENT0,GLES20.GL_TEXTURE_2D,getTexture(),0);
  int status=GLES20.glCheckFramebufferStatus(GLES20.GL_FRAMEBUFFER);
  if (status == GLES20.GL_FRAMEBUFFER_COMPLETE) {
    GLES20.glViewport(0,0,(int)size.width,(int)size.height);
    action.run();
  }
  GLES20.glBindFramebuffer(GLES20.GL_FRAMEBUFFER,0);
  if (!isSuppressingChanges() && delegate != null) {
    delegate.contentChanged(bounds);
  }
}
