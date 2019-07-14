/** 
 * ??App??
 * @param context     ???
 * @param packageName ??
 * @return App??
 */
@SuppressLint("PackageManagerGetSignatures") public static Signature[] getAppSignature(Context context,String packageName){
  if (RxDataTool.isNullString(packageName))   return null;
  try {
    PackageManager pm=context.getPackageManager();
    PackageInfo pi=pm.getPackageInfo(packageName,PackageManager.GET_SIGNATURES);
    return pi == null ? null : pi.signatures;
  }
 catch (  PackageManager.NameNotFoundException e) {
    e.printStackTrace();
    return null;
  }
}
