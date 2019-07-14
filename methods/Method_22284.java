/** 
 * @param permission Manifest.permission to check whether it has been granted.
 * @return true if the permission has been granted to the app, false if it hasn't been granted or the PackageManager could not be contacted.
 */
public boolean hasPermission(@NonNull String permission){
  final PackageManager pm=context.getPackageManager();
  if (pm == null) {
    return false;
  }
  try {
    return pm.checkPermission(permission,context.getPackageName()) == PackageManager.PERMISSION_GRANTED;
  }
 catch (  Exception e) {
    return false;
  }
}
