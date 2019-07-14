private static boolean canResolveBroadcast(Intent intent){
  PackageManager packageManager=ApplicationLoader.applicationContext.getPackageManager();
  List<ResolveInfo> receivers=packageManager.queryBroadcastReceivers(intent,0);
  return receivers != null && receivers.size() > 0;
}
