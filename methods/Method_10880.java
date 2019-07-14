/** 
 * ??????
 * @param mContext ??
 * @param packageName ??? ??
 * @return ??
 */
public static boolean haveExistPackageName(Context mContext,String packageName){
  List<PackageInfo> packageInfos=initPackageManager(mContext);
  List<String> mPackageNames=new ArrayList<>();
  if (packageInfos != null) {
    for (int i=0; i < packageInfos.size(); i++) {
      mPackageNames.add(packageInfos.get(i).packageName);
    }
  }
  return mPackageNames.contains(packageName);
}
