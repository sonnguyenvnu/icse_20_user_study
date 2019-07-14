/** 
 * ???????? <p>?????  {@code <uses-permission android:name="android.permission.KILL_BACKGROUND_PROCESSES"/>}</p>
 * @return ??????????
 */
public static Set<String> killAllBackgroundProcesses(Context context){
  ActivityManager am=(ActivityManager)context.getSystemService(Context.ACTIVITY_SERVICE);
  List<ActivityManager.RunningAppProcessInfo> infos=am.getRunningAppProcesses();
  Set<String> set=new HashSet<>();
  for (  ActivityManager.RunningAppProcessInfo info : infos) {
    for (    String pkg : info.pkgList) {
      am.killBackgroundProcesses(pkg);
      set.add(pkg);
    }
  }
  infos=am.getRunningAppProcesses();
  for (  ActivityManager.RunningAppProcessInfo info : infos) {
    for (    String pkg : info.pkgList) {
      set.remove(pkg);
    }
  }
  return set;
}
