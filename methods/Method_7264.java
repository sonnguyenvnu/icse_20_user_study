/** 
 * Used to check whether there is a specialized handler for a given intent.
 * @param intent The intent to check with.
 * @return Whether there is a specialized handler for the given intent.
 */
private static boolean hasSpecializedHandlerIntents(Context context,Intent intent){
  try {
    PackageManager pm=context.getPackageManager();
    List<ResolveInfo> handlers=pm.queryIntentActivities(intent,PackageManager.GET_RESOLVED_FILTER);
    if (handlers == null || handlers.size() == 0) {
      return false;
    }
    for (    ResolveInfo resolveInfo : handlers) {
      IntentFilter filter=resolveInfo.filter;
      if (filter == null)       continue;
      if (filter.countDataAuthorities() == 0 || filter.countDataPaths() == 0)       continue;
      if (resolveInfo.activityInfo == null)       continue;
      return true;
    }
  }
 catch (  RuntimeException e) {
    Log.e(TAG,"Runtime exception while getting specialized handlers");
  }
  return false;
}
