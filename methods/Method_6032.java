@TargetApi(24) private static @SecureMode int getSecureModeV24(Context context){
  if (Util.SDK_INT < 26 && ("samsung".equals(Util.MANUFACTURER) || "XT1650".equals(Util.MODEL))) {
    return SECURE_MODE_NONE;
  }
  if (Util.SDK_INT < 26 && !context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_VR_MODE_HIGH_PERFORMANCE)) {
    return SECURE_MODE_NONE;
  }
  EGLDisplay display=EGL14.eglGetDisplay(EGL14.EGL_DEFAULT_DISPLAY);
  String eglExtensions=EGL14.eglQueryString(display,EGL10.EGL_EXTENSIONS);
  if (eglExtensions == null) {
    return SECURE_MODE_NONE;
  }
  if (!eglExtensions.contains(EXTENSION_PROTECTED_CONTENT)) {
    return SECURE_MODE_NONE;
  }
  return eglExtensions.contains(EXTENSION_SURFACELESS_CONTEXT) ? SECURE_MODE_SURFACELESS_CONTEXT : SECURE_MODE_PROTECTED_PBUFFER;
}
