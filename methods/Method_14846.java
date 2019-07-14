/** 
 * ????Activity?Intent
 * @param context
 * @param isToSetting true-?????false-??????
 * @return
 */
public static Intent createIntent(Context context,boolean isToSetting){
  return createIntent(context,null).putExtra(INTENT_IS_TO_SETTING,isToSetting);
}
