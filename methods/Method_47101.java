/** 
 * Determines whether cloud provider is installed or not
 */
public static final boolean isCloudProviderAvailable(Context context){
  PackageManager pm=context.getPackageManager();
  try {
    pm.getPackageInfo(CloudContract.APP_PACKAGE_NAME,PackageManager.GET_ACTIVITIES);
    return true;
  }
 catch (  PackageManager.NameNotFoundException e) {
    return false;
  }
}
