/** 
 * ????Activity?Intent
 * @param context
 * @param userId
 * @return
 */
public static Intent createIntent(Context context,long userId){
  return new Intent(context,QRCodeActivity.class).putExtra(INTENT_ID,userId);
}
