/** 
 * ??App???Debug??
 * @param context ???
 * @return {@code true}: ?<br> {@code false}: ?
 */
public static boolean isAppDebug(Context context){
  return isAppDebug(context,context.getPackageName());
}
