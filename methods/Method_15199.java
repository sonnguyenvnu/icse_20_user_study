/** 
 * ???????????????
 * @param configList
 * @param lastList
 */
public final void init(ArrayList<GridPickerConfig> configList,List<Entry<Integer,String>> lastList){
  if (configList == null || configList.size() <= 0) {
    Log.e(TAG,"initTabs  (configList == null || configList.size() <= 0 " + ">> selectedItemPostionList = new ArrayList<Integer>(); return;");
    return;
  }
  currentTabPosition=configList.size() - 1;
  currentTabName=configList.get(currentTabPosition).getTabName();
  int tabWidth=configList.size() < 4 ? ScreenUtil.getScreenWidth(context) / configList.size() : 3;
  llGridPickerViewTabContainer.removeAllViews();
  for (int i=0; i < configList.size(); i++) {
    addTab(i,tabWidth,StringUtil.getTrimedString(configList.get(i)));
  }
  llGridPickerViewTabContainer.getChildAt(currentTabPosition).setBackgroundResource(R.color.alpha_3);
  this.configList=configList;
  bindView(currentTabPosition,lastList,configList.get(currentTabPosition).getSelectedItemPostion());
}
