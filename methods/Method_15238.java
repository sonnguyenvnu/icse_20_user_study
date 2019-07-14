/** 
 * ??tab
 * @param position
 * @param tabType
 */
public void select(int position){
  Log.i(TAG,"select  position = " + position);
  if (position < 0 || position >= getCount()) {
    Log.e(TAG,"select  position < 0 || position >= getCount() >> return;");
    return;
  }
  for (int i=0; i < tvTabs.length; i++) {
    tvTabs[i].setSelected(i == position);
  }
  if (onTabSelectedListener != null) {
    onTabSelectedListener.onTabSelected(tvTabs[position],position,tvTabs[position].getId());
  }
  this.currentPosition=position;
}
