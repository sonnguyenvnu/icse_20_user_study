/** 
 * ???????//?????????
 * @param tabPosition
 * @param list
 */
public void bindView(final int tabPosition,List<Entry<Integer,String>> list){
  bindView(tabPosition,list,getSelectedItemPosition(tabPosition));
}
