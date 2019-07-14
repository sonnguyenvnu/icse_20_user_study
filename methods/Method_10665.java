/** 
 * ????App???????? <p>?????  {@code <uses-permission android:name="android.permission.GET_TASKS"/>}</p> <p>???????????????</p>
 * @param context ???
 * @return {@code true}: ??<br> {@code false}: ??
 */
public static boolean isAppBackground(Context context){
  ActivityManager am=(ActivityManager)context.getSystemService(Context.ACTIVITY_SERVICE);
  @SuppressWarnings("deprecation") List<ActivityManager.RunningTaskInfo> tasks=am.getRunningTasks(1);
  if (!tasks.isEmpty()) {
    ComponentName topActivity=tasks.get(0).topActivity;
    return !topActivity.getPackageName().equals(context.getPackageName());
  }
  return false;
}
