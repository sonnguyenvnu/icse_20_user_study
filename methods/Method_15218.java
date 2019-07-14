/** 
 * ????Activity?Intent
 * @param context
 * @param normalAddress
 * @param testAddress
 * @param sharedPreferencesPath
 * @param pathMode
 * @param normalKey
 * @param testKey
 * @return
 */
public static Intent createIntent(Context context,String normalAddress,String testAddress,String sharedPreferencesPath,int pathMode,String normalKey,String testKey){
  return new Intent(context,ServerSettingActivity.class).putExtra(INTENT_NORMAL_ADDRESS,normalAddress).putExtra(INTENT_TEST_ADDRESS,testAddress).putExtra(INTENT_SHARED_PREFERENCES_PATH,sharedPreferencesPath).putExtra(INTENT_PATH_MODE,pathMode).putExtra(INTENT_NORMAL_KEY,normalKey).putExtra(INTENT_TEST_KEY,testKey);
}
