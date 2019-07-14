/** 
 * ????boolean?
 * @param context
 * @return
 */
public static boolean[] getAllBooleans(Context context){
  init(context);
  return new boolean[]{cache,preload,voice,vibrate,noDisturb,isOnTestMode,isFirstStart};
}
