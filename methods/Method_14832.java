/** 
 * ????Activity?Intent
 * @param context
 * @return
 */
public static Intent createIntent(Context context){
  return createIntent(context,MomentListFragment.RANGE_USER_CIRCLE,APIJSONApplication.getInstance().getCurrentUserId());
}
