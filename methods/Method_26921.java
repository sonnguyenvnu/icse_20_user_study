/** 
 * Returns is transition animations enabled. Animations was disabled for Android versions < 4.0
 */
public static boolean isTransitionsAllowed(){
  return Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH;
}
