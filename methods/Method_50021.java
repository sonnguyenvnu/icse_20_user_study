/** 
 * Check if read permissions for SMS have been granted
 */
private boolean checkReadSmsPermissions(){
  return Build.VERSION.SDK_INT < Build.VERSION_CODES.M || mContext.checkSelfPermission(Manifest.permission.READ_SMS) == PackageManager.PERMISSION_GRANTED;
}
