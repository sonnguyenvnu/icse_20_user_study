/** 
 * Whether a browser receiving the given intent should always use browser UI and avoid using any custom tabs UI.
 * @param intent The intent to check for the required flags and extras.
 * @return Whether the browser UI should be used exclusively.
 */
public static boolean shouldAlwaysUseBrowserUI(Intent intent){
  return intent.getBooleanExtra(EXTRA_USER_OPT_OUT_FROM_CUSTOM_TABS,false) && (intent.getFlags() & Intent.FLAG_ACTIVITY_NEW_TASK) != 0;
}
