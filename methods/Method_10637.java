/** 
 * ????????Activity
 * @param context     ???
 * @param packageName ??
 * @param className   activity?????
 * @return {@code true}: ?<br> {@code false}: ?
 */
public static boolean isExistActivity(Context context,String packageName,String className){
  Intent intent=new Intent();
  intent.setClassName(packageName,className);
  return !(context.getPackageManager().resolveActivity(intent,0) == null || intent.resolveActivity(context.getPackageManager()) == null || context.getPackageManager().queryIntentActivities(intent,0).size() == 0);
}
