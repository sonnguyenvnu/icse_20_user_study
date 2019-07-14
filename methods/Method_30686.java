public static void allowRestrictedHiddenApiAccess(){
  if (Build.VERSION.SDK_INT < Build.VERSION_CODES.P) {
    return;
  }
synchronized (sRestrictedHiddenApiAccessAllowedLock) {
    if (!sRestrictedHiddenApiAccessAllowed) {
      sClassClassLoaderField.setObject(ReflectedAccessor.class,null);
      sRestrictedHiddenApiAccessAllowed=true;
    }
  }
}
