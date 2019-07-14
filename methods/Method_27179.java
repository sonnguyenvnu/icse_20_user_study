private static boolean isInstalledFromPlaySore(@NonNull Context context){
  final String ipn=context.getPackageManager().getInstallerPackageName(BuildConfig.APPLICATION_ID);
  return !InputHelper.isEmpty(ipn);
}
