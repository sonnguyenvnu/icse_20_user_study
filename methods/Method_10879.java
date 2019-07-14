public static List<PackageInfo> initPackageManager(Context mContext){
  PackageManager mPackageManager=mContext.getPackageManager();
  return mPackageManager.getInstalledPackages(0);
}
