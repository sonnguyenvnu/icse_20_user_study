/** 
 * Returns the preferred package to use for Custom Tabs. The preferred package name is the default VIEW intent handler as long as it supports Custom Tabs. To modify this preferred behavior, set <code>ignoreDefault</code> to true and give a non empty list of package names in <code>packages</code>.
 * @param context       {@link Context} to use for querying the packages.
 * @param packages      Ordered list of packages to test for Custom Tabs support, indecreasing order of priority.
 * @param ignoreDefault If set, the default VIEW handler won't get priority over other browsers.
 * @return The preferred package name for handling Custom Tabs, or <code>null</code>.
 */
public static String getPackageName(Context context,@Nullable List<String> packages,boolean ignoreDefault){
  PackageManager pm=context.getPackageManager();
  List<String> packageNames=packages == null ? new ArrayList<String>() : packages;
  Intent activityIntent=new Intent(Intent.ACTION_VIEW,Uri.parse("http://"));
  if (!ignoreDefault) {
    ResolveInfo defaultViewHandlerInfo=pm.resolveActivity(activityIntent,0);
    if (defaultViewHandlerInfo != null) {
      String packageName=defaultViewHandlerInfo.activityInfo.packageName;
      packageNames=new ArrayList<>(packageNames.size() + 1);
      packageNames.add(packageName);
      if (packages != null)       packageNames.addAll(packages);
    }
  }
  Intent serviceIntent=new Intent(CustomTabsService.ACTION_CUSTOM_TABS_CONNECTION);
  for (  String packageName : packageNames) {
    serviceIntent.setPackage(packageName);
    if (pm.resolveService(serviceIntent,0) != null)     return packageName;
  }
  return null;
}
