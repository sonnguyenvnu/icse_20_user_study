/** 
 * ??App???
 * @param context
 * @return
 */
public static int getAppVersionNo(Context context){
  PackageManager packageManager=context.getPackageManager();
  PackageInfo packInfo=null;
  try {
    packInfo=packageManager.getPackageInfo(context.getPackageName(),0);
  }
 catch (  PackageManager.NameNotFoundException e) {
    e.printStackTrace();
  }
  int version=packInfo.versionCode;
  return version;
}
