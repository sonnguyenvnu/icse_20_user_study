private void init(final int version){
  this.setEGLContextClientVersion(version);
  if (version >= 2 && VERSION.SDK_INT >= VERSION_CODES.JELLY_BEAN_MR2) {
    this.setEGLConfigChooser(new EGLConfigChooser(){
      @Override public EGLConfig chooseConfig(      final EGL10 egl,      final EGLDisplay display){
        EGLConfig[] configs=new EGLConfig[1];
        int[] numConfigs=new int[1];
        egl.eglChooseConfig(display,EglEncoder.EGL_ATTRIB_LIST,configs,configs.length,numConfigs);
        return configs[0];
      }
    }
);
  }
 else {
    this.setEGLConfigChooser(8,8,8,8,16,0);
  }
  this.getHolder().setFormat(PixelFormat.TRANSLUCENT);
  this.setRenderer(this);
}
