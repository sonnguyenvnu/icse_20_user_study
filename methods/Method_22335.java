/** 
 * @return app version or 0 if PackageInfo was not available.
 */
private int getAppVersion(@NonNull Context context){
  final PackageManagerWrapper packageManagerWrapper=new PackageManagerWrapper(context);
  final PackageInfo packageInfo=packageManagerWrapper.getPackageInfo();
  return (packageInfo == null) ? 0 : packageInfo.versionCode;
}
