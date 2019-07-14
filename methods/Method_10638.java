/** 
 * ?????Activity
 * @param context     ???
 * @param packageName ??
 * @param className   ???
 * @param bundle      bundle
 */
public static void launchActivity(Context context,String packageName,String className,Bundle bundle){
  context.startActivity(RxIntentTool.getComponentNameIntent(packageName,className,bundle));
}
