@Override public EglContextWrapper start(EglContextWrapper eglContext){
  FileLogger.w(TAG,"start() tid=" + Thread.currentThread().getId());
  mEglDisplay=EGL14.eglGetDisplay(EGL14.EGL_DEFAULT_DISPLAY);
  if (mEglDisplay == EGL14.EGL_NO_DISPLAY) {
    throw new RuntimeException("eglGetDisplay failed");
  }
  int[] version=new int[2];
  if (!EGL14.eglInitialize(mEglDisplay,version,0,version,1)) {
    mEglDisplay=null;
    throw new RuntimeException("eglInitialize failed");
  }
  mEglConfig=eglConfigChooser.chooseConfig(mEglDisplay,false);
  mEglContext=eglContextFactory.createContextAPI17(mEglDisplay,mEglConfig,eglContext.getEglContext());
  if (mEglContext == null || mEglContext == EGL14.EGL_NO_CONTEXT) {
    FileLogger.d(TAG,"mEglContext:" + mEglContext);
    mEglContext=null;
    throwEglException("; createContext");
  }
  FileLogger.w(TAG,"createContext " + mEglContext + " tid=" + Thread.currentThread().getId());
  mEglSurface=null;
  EglContextWrapper eglContextWrapper=new EglContextWrapper();
  eglContextWrapper.setEglContext(mEglContext);
  return eglContextWrapper;
}
