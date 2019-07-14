/** 
 * ??????
 * @param context ???
 * @return {@code true}: ?<br> {@code false}: ?
 */
public static boolean isScreenLock(Context context){
  KeyguardManager km=(KeyguardManager)context.getSystemService(Context.KEYGUARD_SERVICE);
  return km.inKeyguardRestrictedInputMode();
}
