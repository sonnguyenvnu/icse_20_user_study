public void paintStroke(final Path path,final boolean clearBuffer,final Runnable action){
  renderView.performInContext(new Runnable(){
    @Override public void run(){
      activePath=path;
      RectF bounds=null;
      GLES20.glBindFramebuffer(GLES20.GL_FRAMEBUFFER,getReusableFramebuffer());
      GLES20.glFramebufferTexture2D(GLES20.GL_FRAMEBUFFER,GLES20.GL_COLOR_ATTACHMENT0,GLES20.GL_TEXTURE_2D,getPaintTexture(),0);
      Utils.HasGLError();
      int status=GLES20.glCheckFramebufferStatus(GLES20.GL_FRAMEBUFFER);
      if (status == GLES20.GL_FRAMEBUFFER_COMPLETE) {
        GLES20.glViewport(0,0,(int)size.width,(int)size.height);
        if (clearBuffer) {
          GLES20.glClearColor(0,0,0,0);
          GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);
        }
        if (shaders == null) {
          return;
        }
        Shader shader=shaders.get(brush.isLightSaber() ? "brushLight" : "brush");
        if (shader == null) {
          return;
        }
        GLES20.glUseProgram(shader.program);
        if (brushTexture == null) {
          brushTexture=new Texture(brush.getStamp());
        }
        GLES20.glActiveTexture(GLES20.GL_TEXTURE0);
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D,brushTexture.texture());
        GLES20.glUniformMatrix4fv(shader.getUniform("mvpMatrix"),1,false,FloatBuffer.wrap(projection));
        GLES20.glUniform1i(shader.getUniform("texture"),0);
        bounds=Render.RenderPath(path,renderState);
      }
      GLES20.glBindFramebuffer(GLES20.GL_FRAMEBUFFER,0);
      if (delegate != null) {
        delegate.contentChanged(bounds);
      }
      if (activeStrokeBounds != null) {
        activeStrokeBounds.union(bounds);
      }
 else {
        activeStrokeBounds=bounds;
      }
      if (action != null) {
        action.run();
      }
    }
  }
);
}
