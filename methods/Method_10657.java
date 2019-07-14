/** 
 * ??App??
 * @param context ???
 * @return App??
 */
public static String getAppPath(Context context){
  return getAppPath(context,context.getPackageName());
}
