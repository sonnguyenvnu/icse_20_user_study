private int getReusableFramebuffer(){
  if (reusableFramebuffer == 0) {
    int[] buffers=new int[1];
    GLES20.glGenFramebuffers(1,buffers,0);
    reusableFramebuffer=buffers[0];
    Utils.HasGLError();
  }
  return reusableFramebuffer;
}
