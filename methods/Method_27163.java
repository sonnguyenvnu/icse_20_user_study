public static void activateLinkInterceptorActivity(Context context,boolean activate){
  final PackageManager pm=context.getPackageManager();
  final int flag=activate ? PackageManager.COMPONENT_ENABLED_STATE_ENABLED : PackageManager.COMPONENT_ENABLED_STATE_DISABLED;
  pm.setComponentEnabledSetting(new ComponentName(context,LinksParserActivity.class),flag,PackageManager.DONT_KILL_APP);
}
