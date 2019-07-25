private void init(EGLContext shared_context,final boolean with_depth_buffer,final boolean isRecordable){
  if (DEBUG)   Log.v(TAG,"init:");
  if (mEglDisplay != EGL14.EGL_NO_DISPLAY) {
    throw new RuntimeException("EGL already set up");
  }
  mEglDisplay=EGL14.eglGetDisplay(EGL14.EGL_DEFAULT_DISPLAY);
  if (mEglDisplay == EGL14.EGL_NO_DISPLAY) {
    throw new RuntimeException("eglGetDisplay failed");
  }
  final int[] version=new int[2];
  if (!EGL14.eglInitialize(mEglDisplay,version,0,version,1)) {
    mEglDisplay=null;
    throw new RuntimeException("eglInitialize failed");
  }
  shared_context=shared_context != null ? shared_context : EGL14.EGL_NO_CONTEXT;
  if (mEglContext == EGL14.EGL_NO_CONTEXT) {
    mEglConfig=getConfig(with_depth_buffer,isRecordable);
    if (mEglConfig == null) {
      throw new RuntimeException("chooseConfig failed");
    }
    mEglContext=createContext(shared_context);
  }
  final int[] values=new int[1];
  EGL14.eglQueryContext(mEglDisplay,mEglContext,EGL14.EGL_CONTEXT_CLIENT_VERSION,values,0);
  if (DEBUG)   Log.d(TAG,"EGLContext created, client version " + values[0]);
  makeDefault();
}
