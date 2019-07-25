/** 
 * Creates LauncherActivityMetadata instance based on metadata of the passed Activity.
 */
public static LauncherActivityMetadata parse(Activity activity){
  Bundle metaData=null;
  try {
    metaData=activity.getPackageManager().getActivityInfo(new ComponentName(activity,activity.getClass()),PackageManager.GET_META_DATA).metaData;
  }
 catch (  PackageManager.NameNotFoundException e) {
  }
  return new LauncherActivityMetadata(metaData == null ? new Bundle() : metaData);
}
