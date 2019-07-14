/** 
 * ??BottomMenuWindow?Intent
 * @param context
 * @param names
 * @param idList
 * @return
 */
public static Intent createIntent(Context context,String[] names,ArrayList<Integer> idList){
  return new Intent(context,BottomMenuWindow.class).putExtra(INTENT_ITEMS,names).putExtra(INTENT_ITEM_IDS,idList);
}
