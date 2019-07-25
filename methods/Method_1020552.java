protected void init(){
  setZOrderOnTop(true);
  setEGLContextClientVersion(2);
  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
    setEGLConfigChooser(8,8,8,8,16,0);
  }
 else {
    setEGLConfigChooser(5,6,5,8,0,0);
  }
  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
    setPreserveEGLContextOnPause(true);
  }
  getHolder().setFormat(PixelFormat.TRANSLUCENT);
  setRenderer(this);
  setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
}
