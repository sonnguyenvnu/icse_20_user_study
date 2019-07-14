/** 
 * ???????????? intent
 * @return
 */
public static Intent getAppDetailsSettingsIntent(Context mContext){
  Intent localIntent=new Intent();
  localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
  if (Build.VERSION.SDK_INT >= 9) {
    localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
    localIntent.setData(Uri.fromParts("package",mContext.getPackageName(),null));
  }
 else   if (Build.VERSION.SDK_INT <= 8) {
    localIntent.setAction(Intent.ACTION_VIEW);
    localIntent.setClassName("com.android.settings","com.android.settings.InstalledAppDetails");
    localIntent.putExtra("com.android.settings.ApplicationPkgName",mContext.getPackageName());
  }
  return localIntent;
}
