/** 
 * Adds the necessary flags and extras to signal any browser supporting custom tabs to use the browser UI at all times and avoid showing custom tab like UI. Calling this with an intent will override any custom tabs related customizations.
 * @param intent The intent to modify for always showing browser UI.
 * @return The same intent with the necessary flags and extras added.
 */
public static Intent setAlwaysUseBrowserUI(Intent intent){
  if (intent == null)   intent=new Intent(Intent.ACTION_VIEW);
  intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
  intent.putExtra(EXTRA_USER_OPT_OUT_FROM_CUSTOM_TABS,true);
  return intent;
}
