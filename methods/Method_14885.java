/** 
 * ????Activity?Intent
 * @param context
 * @return
 */
public static Intent createIntent(Context context){
  return createIntent(context,UserListFragment.RANGE_USER_CIRCLE,APIJSONApplication.getInstance().getCurrentUserId());
}
