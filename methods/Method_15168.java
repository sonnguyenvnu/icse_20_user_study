/** 
 * ??BottomMenuWindow?Intent
 * @param context
 * @param names
 * @param ids
 * @return
 */
public static Intent createIntent(Context context,String[] names,int[] ids){
  return new Intent(context,BottomMenuWindow.class).putExtra(INTENT_ITEMS,names).putExtra(INTENT_ITEM_IDS,ids);
}
