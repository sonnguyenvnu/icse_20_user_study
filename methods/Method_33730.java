/** 
 * ?????????????????
 */
public static boolean isHandlePermission(Activity activity,String permission){
  if (isPermission(activity,permission)) {
    return true;
  }
 else {
    int permissionCheck=ContextCompat.checkSelfPermission(activity,permission);
    if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
      ActivityCompat.requestPermissions(activity,new String[]{permission},PERMISSION_CODE_ONE);
    }
    return false;
  }
}
