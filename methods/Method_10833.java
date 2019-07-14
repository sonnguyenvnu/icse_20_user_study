/** 
 * ????App(??7.0)???
 * @param context
 * @param filePath
 * @return
 */
public static Intent getInstallAppIntent(Context context,String filePath){
  File apkfile=new File(filePath);
  if (!apkfile.exists()) {
    return null;
  }
  Intent intent=new Intent(Intent.ACTION_VIEW);
  Uri contentUri=RxFileTool.getUriForFile(context,apkfile);
  intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
  }
  intent.setDataAndType(contentUri,"application/vnd.android.package-archive");
  return intent;
}
