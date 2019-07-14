/** 
 * ??TopMenuWindow?Intent
 * @param context
 * @param title - ???
 * @param names
 * @param intentCodeList
 * @return
 */
public static Intent createIntent(Context context,String[] names,ArrayList<Integer> intentCodeList){
  return new Intent(context,TopMenuWindow.class).putExtra(INTENT_NAMES,names).putExtra(INTENT_INTENTCODES,intentCodeList);
}
