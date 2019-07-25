/** 
 * APK????
 * @param info ???apk??
 * @return ???apk?????????????true?????false
 */
private static boolean compare(PackageInfo info){
  if (info == null) {
    return false;
  }
  if (info.packageName.equals(AppUtils.getPackageName(BaseApplication.getContext()))) {
    if (info.versionCode > AppUtils.getVersionCode(BaseApplication.getContext())) {
      return true;
    }
  }
  return false;
}
