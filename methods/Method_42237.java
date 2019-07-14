/** 
 * Initialise the Prefs object for future static usage. Make sure to initialise this in Application class.
 * @param context The context to initialise with.
 */
public static void init(@NonNull Context context){
  if (sharedPrefs != null) {
    throw new RuntimeException("Prefs has already been instantiated");
  }
  sharedPrefs=new SharedPrefs(context);
}
