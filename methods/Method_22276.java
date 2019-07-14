/** 
 * Returns true if the application is debuggable.
 * @return true if the application is debuggable.
 */
private boolean isDebuggable(){
  final PackageManager pm=context.getPackageManager();
  try {
    return (pm.getApplicationInfo(context.getPackageName(),0).flags & ApplicationInfo.FLAG_DEBUGGABLE) > 0;
  }
 catch (  PackageManager.NameNotFoundException e) {
    return false;
  }
}
