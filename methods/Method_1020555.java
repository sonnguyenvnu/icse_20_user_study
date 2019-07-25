/** 
 * Initialize EGL for a given configuration spec.
 * @param eglContext
 */
@Override public EglContextWrapper start(EglContextWrapper eglContext){
  FileLogger.w("EglHelper","start() tid=" + Thread.currentThread().getId());
  mEgl=(EGL10)EGLContext.getEGL();
  mEglDisplay=mEgl.eglGetDisplay(EGL10.EGL_DEFAULT_DISPLAY);
  if (mEglDisplay == EGL10.EGL_NO_DISPLAY) {
    throw new RuntimeException("eglGetDisplay failed");
  }
  int[] version=new int[2];
  if (!mEgl.eglInitialize(mEglDisplay,version)) {
    throw new RuntimeException("eglInitialize failed");
  }
  mEglConfig=eglConfigChooser.chooseConfig(mEgl,mEglDisplay);
  mEglContext=eglContextFactory.createContext(mEgl,mEglDisplay,mEglConfig,eglContext.getEglContextOld());
  if (mEglContext == null || mEglContext == EGL10.EGL_NO_CONTEXT) {
    mEglContext=null;
    throwEglException("createContext");
  }
  FileLogger.w("EglHelper","createContext " + mEglContext + " tid=" + Thread.currentThread().getId());
  mEglSurface=null;
  EglContextWrapper eglContextWrapper=new EglContextWrapper();
  eglContextWrapper.setEglContextOld(mEglContext);
  return eglContextWrapper;
}
