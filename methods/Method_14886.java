/** 
 * ????Activity?Intent
 * @param context
 * @param range
 * @param id
 * @param search
 * @param showSearch
 * @return
 */
public static Intent createIntent(Context context,int range,long id,JSONObject search,boolean showSearch){
  return new Intent(context,UserListActivity.class).putExtra(INTENT_RANGE,range).putExtra(INTENT_ID,id).putExtra(INTENT_SEARCH,JSON.toJSONString(search)).putExtra(INTENT_SHOW_SEARCH,showSearch);
}
