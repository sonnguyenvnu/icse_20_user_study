/** 
 * ?onItemSelected???, ???????????tab??? tabPositionWhenItemSelect+=1; selectedItemPosition = 0;
 * @param tabPosition
 * @param itemPosition
 * @param itemName
 */
public void doOnItemSelected(int tabPosition,int itemPosition,String itemName){
  currentTabPosition=tabPosition < getTabCount() ? tabPosition : getTabCount() - 1;
  currentSelectedItemPosition=itemPosition;
  currentSelectedItemName=StringUtil.getTrimedString(itemName);
  configList.set(currentTabPosition,configList.get(currentTabPosition).set(currentSelectedItemName,itemPosition));
  for (int i=0; i < llGridPickerViewTabContainer.getChildCount(); i++) {
    ((TextView)llGridPickerViewTabContainer.getChildAt(i)).setText("" + configList.get(i).getTabName());
    llGridPickerViewTabContainer.getChildAt(i).setBackgroundResource(i == tabPosition ? R.color.alpha_3 : R.color.alpha_complete);
  }
}
