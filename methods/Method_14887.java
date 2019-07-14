/** 
 * ????Activity?Intent
 * @param context
 * @param idList
 * @return
 */
public static Intent createIntent(Context context,List<Long> idList){
  return new Intent(context,UserListActivity.class).putExtra(INTENT_ID_LIST,(Serializable)idList);
}
