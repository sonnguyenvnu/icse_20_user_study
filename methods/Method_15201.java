/** 
 * @param tabPosition
 * @param list
 * @param itemPosition
 */
public void bindView(final int tabPosition,List<Entry<Integer,String>> list,int itemPosition){
  if (configList == null || configList.size() <= 0) {
    Log.e(TAG,"bindView(final int tabPostion, final List<Entry<Integer, String>> list, final int itemPosition) {" + " >> configList == null || configList.size() <= 0 >> return;");
    return;
  }
  GridPickerConfig gpcb=configList.get(tabPosition);
  if (gpcb == null) {
    return;
  }
  if (list == null || list.size() <= 0) {
    Log.e(TAG,"bindView(final int tabPostion, final List<Entry<Integer, String>> list, final int itemPosition) {" + " >> list == null || list.size() <= 0 >> return;");
    return;
  }
  if (tabPosition >= MAX_NUM_TABS) {
    Log.e(TAG,"bindView  tabPosition >= MAX_NUM_TABS,?????????????????????? >> return;");
    return;
  }
  itemPosition=getItemPosition(itemPosition,list);
  int numColumns=gpcb.getNumColumns();
  if (numColumns <= 0) {
    numColumns=3;
  }
  int maxShowRows=gpcb.getMaxShowRows();
  if (maxShowRows <= 0) {
    maxShowRows=5;
  }
  doOnItemSelected(tabPosition,itemPosition,list.get(itemPosition).getValue());
  adapter=new GridPickerAdapter(context,itemPosition,contentHeight / maxShowRows);
  adapter.refresh(list);
  adapter.setOnItemSelectedListener(new OnItemSelectedListener(){
    @Override public void onItemSelected(    AdapterView<?> parent,    View view,    int position,    long id){
      currentSelectedItemName=adapter.getCurrentItemName();
      if (isOnLastTab() == false && onItemSelectedListener != null) {
        onItemSelectedListener.onItemSelected(parent,view,position,id);
        return;
      }
      doOnItemSelected(tabPosition,position,adapter.getCurrentItemName());
    }
    @Override public void onNothingSelected(    AdapterView<?> parent){
    }
  }
);
  gvGridPickerView.setNumColumns(numColumns);
  gvGridPickerView.setAdapter(adapter);
  gvGridPickerView.smoothScrollToPosition(itemPosition);
}
