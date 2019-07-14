/** 
 * ????Activity?Intent
 * @param context
 * @param packageName
 * @param minLevel
 * @param maxLevel
 * @return
 */
public static Intent createIntent(Context context,String packageName,int minLevel,int maxLevel){
  return new Intent(context,PlacePickerWindow.class).putExtra(INTENT_PACKAGE_NAME,packageName).putExtra(INTENT_MIN_LEVEL,minLevel).putExtra(INTENT_MAX_LEVEL,maxLevel);
}
