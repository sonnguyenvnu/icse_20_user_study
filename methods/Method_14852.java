/** 
 * ????Activity?Intent
 * @param context
 * @param phone
 * @param password
 * @return
 */
public static Intent createIntent(Context context,int type,String phone,String password){
  return new Intent(context,PasswordActivity.class).putExtra(INTENT_TYPE,type).putExtra(INTENT_PHONE,phone).putExtra(INTENT_PASSWORD,password);
}
