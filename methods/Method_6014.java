/** 
 * Returns a user agent string based on the given application name and the library version.
 * @param context A valid context of the calling application.
 * @param applicationName String that will be prefix'ed to the generated user agent.
 * @return A user agent string generated using the applicationName and the library version.
 */
public static String getUserAgent(Context context,String applicationName){
  String versionName;
  try {
    String packageName=context.getPackageName();
    PackageInfo info=context.getPackageManager().getPackageInfo(packageName,0);
    versionName=info.versionName;
  }
 catch (  NameNotFoundException e) {
    versionName="?";
  }
  return applicationName + "/" + versionName + " (Linux;Android " + Build.VERSION.RELEASE + ") " + ExoPlayerLibraryInfo.VERSION_SLASHY;
}
