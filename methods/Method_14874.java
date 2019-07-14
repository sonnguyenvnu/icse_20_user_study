/** 
 * ????UserActivity?intent
 * @param context
 * @param id
 * @return
 */
public static Intent createIntent(Context context,long id){
  return new Intent(context,UserActivity.class).putExtra(INTENT_ID,id);
}
