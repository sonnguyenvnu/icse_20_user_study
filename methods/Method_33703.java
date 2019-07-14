/** 
 * ????????????
 * @param context
 * @param appPackageName ????
 * @return true????false????
 */
public static boolean isApplicationAvilible(Context context,String appPackageName){
  try {
    PackageManager packageManager=context.getPackageManager();
    List<PackageInfo> pinfo=packageManager.getInstalledPackages(0);
    if (pinfo != null) {
      for (int i=0; i < pinfo.size(); i++) {
        String pn=pinfo.get(i).packageName;
        if (appPackageName.equals(pn)) {
          return true;
        }
      }
    }
    return false;
  }
 catch (  Exception ignored) {
    return false;
  }
}
