private static EGLSurface createEGLSurface(EGLDisplay display,EGLConfig config,EGLContext context,@SecureMode int secureMode){
  EGLSurface surface;
  if (secureMode == SECURE_MODE_SURFACELESS_CONTEXT) {
    surface=EGL14.EGL_NO_SURFACE;
  }
 else {
    int[] pbufferAttributes;
    if (secureMode == SECURE_MODE_PROTECTED_PBUFFER) {
      pbufferAttributes=new int[]{EGL14.EGL_WIDTH,EGL_SURFACE_WIDTH,EGL14.EGL_HEIGHT,EGL_SURFACE_HEIGHT,EGL_PROTECTED_CONTENT_EXT,EGL14.EGL_TRUE,EGL14.EGL_NONE};
    }
 else {
      pbufferAttributes=new int[]{EGL14.EGL_WIDTH,EGL_SURFACE_WIDTH,EGL14.EGL_HEIGHT,EGL_SURFACE_HEIGHT,EGL14.EGL_NONE};
    }
    surface=EGL14.eglCreatePbufferSurface(display,config,pbufferAttributes,0);
    if (surface == null) {
      throw new GlException("eglCreatePbufferSurface failed");
    }
  }
  boolean eglMadeCurrent=EGL14.eglMakeCurrent(display,surface,surface,context);
  if (!eglMadeCurrent) {
    throw new GlException("eglMakeCurrent failed");
  }
  return surface;
}
