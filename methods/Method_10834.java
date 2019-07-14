/** 
 * ????App???
 * @param packageName ??
 * @return ??
 */
public static Intent getUninstallAppIntent(String packageName){
  Intent intent=new Intent(Intent.ACTION_DELETE);
  intent.setData(Uri.parse("package:" + packageName));
  return intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
}
