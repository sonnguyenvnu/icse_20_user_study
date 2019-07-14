public PaintingData getPaintingData(RectF rect,boolean undo){
  int minX=(int)rect.left;
  int minY=(int)rect.top;
  int width=(int)rect.width();
  int height=(int)rect.height();
  GLES20.glGenFramebuffers(1,buffers,0);
  int framebuffer=buffers[0];
  GLES20.glBindFramebuffer(GLES20.GL_FRAMEBUFFER,framebuffer);
  GLES20.glGenTextures(1,buffers,0);
  int texture=buffers[0];
  GLES20.glBindTexture(GLES20.GL_TEXTURE_2D,texture);
  GLES20.glTexParameteri(GL10.GL_TEXTURE_2D,GL10.GL_TEXTURE_MIN_FILTER,GL10.GL_LINEAR);
  GLES20.glTexParameteri(GL10.GL_TEXTURE_2D,GL10.GL_TEXTURE_MAG_FILTER,GL10.GL_LINEAR);
  GLES20.glTexParameteri(GL10.GL_TEXTURE_2D,GL10.GL_TEXTURE_WRAP_S,GL10.GL_CLAMP_TO_EDGE);
  GLES20.glTexParameteri(GL10.GL_TEXTURE_2D,GL10.GL_TEXTURE_WRAP_T,GL10.GL_CLAMP_TO_EDGE);
  GLES20.glTexImage2D(GLES20.GL_TEXTURE_2D,0,GLES20.GL_RGBA,width,height,0,GLES20.GL_RGBA,GLES20.GL_UNSIGNED_BYTE,null);
  GLES20.glFramebufferTexture2D(GLES20.GL_FRAMEBUFFER,GLES20.GL_COLOR_ATTACHMENT0,GLES20.GL_TEXTURE_2D,texture,0);
  GLES20.glViewport(0,0,(int)size.width,(int)size.height);
  if (shaders == null) {
    return null;
  }
  Shader shader=shaders.get(undo ? "nonPremultipliedBlit" : "blit");
  if (shader == null) {
    return null;
  }
  GLES20.glUseProgram(shader.program);
  Matrix translate=new Matrix();
  translate.preTranslate(-minX,-minY);
  float effective[]=GLMatrix.LoadGraphicsMatrix(translate);
  float finalProjection[]=GLMatrix.MultiplyMat4f(projection,effective);
  GLES20.glUniformMatrix4fv(shader.getUniform("mvpMatrix"),1,false,FloatBuffer.wrap(finalProjection));
  if (undo) {
    GLES20.glUniform1i(shader.getUniform("texture"),0);
    GLES20.glActiveTexture(GLES20.GL_TEXTURE0);
    GLES20.glBindTexture(GLES20.GL_TEXTURE_2D,getTexture());
  }
 else {
    GLES20.glUniform1i(shader.getUniform("texture"),0);
    GLES20.glActiveTexture(GLES20.GL_TEXTURE0);
    GLES20.glBindTexture(GLES20.GL_TEXTURE_2D,bitmapTexture.texture());
    GLES20.glActiveTexture(GLES20.GL_TEXTURE0);
    GLES20.glBindTexture(GLES20.GL_TEXTURE_2D,getTexture());
  }
  GLES20.glClearColor(0,0,0,0);
  GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);
  GLES20.glBlendFunc(GLES20.GL_ONE,GLES20.GL_ONE_MINUS_SRC_ALPHA);
  GLES20.glVertexAttribPointer(0,2,GLES20.GL_FLOAT,false,8,vertexBuffer);
  GLES20.glEnableVertexAttribArray(0);
  GLES20.glVertexAttribPointer(1,2,GLES20.GL_FLOAT,false,8,textureBuffer);
  GLES20.glEnableVertexAttribArray(1);
  GLES20.glDrawArrays(GLES20.GL_TRIANGLE_STRIP,0,4);
  dataBuffer.limit(width * height * 4);
  GLES20.glReadPixels(0,0,width,height,GLES20.GL_RGBA,GLES20.GL_UNSIGNED_BYTE,dataBuffer);
  PaintingData data;
  if (undo) {
    data=new PaintingData(null,dataBuffer);
  }
 else {
    Bitmap bitmap=Bitmap.createBitmap(width,height,Bitmap.Config.ARGB_8888);
    bitmap.copyPixelsFromBuffer(dataBuffer);
    data=new PaintingData(bitmap,null);
  }
  buffers[0]=framebuffer;
  GLES20.glDeleteFramebuffers(1,buffers,0);
  buffers[0]=texture;
  GLES20.glDeleteTextures(1,buffers,0);
  return data;
}
