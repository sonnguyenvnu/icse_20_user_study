@Override public void renderbufferStorageMultisample(int target,int samples,int format,int width,int height){
  if (gl2x != null) {
    gl2x.glRenderbufferStorageMultisample(target,samples,format,width,height);
  }
 else   if (gl3 != null) {
    gl3.glRenderbufferStorageMultisample(target,samples,format,width,height);
  }
 else   if (gl3es3 != null) {
    gl3es3.glRenderbufferStorageMultisample(target,samples,format,width,height);
  }
 else {
    throw new RuntimeException(String.format(MISSING_GLFUNC_ERROR,"glRenderbufferStorageMultisample()"));
  }
}
