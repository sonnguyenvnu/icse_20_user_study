/** 
 * ??????
 * @param position 
 */
protected void setTabSelection(int position){
  if (vTabSelectViews == null) {
    Log.e(TAG,"setTabSelection  vTabSelectViews == null >> return;");
    return;
  }
  for (int i=0; i < vTabSelectViews.length; i++) {
    if (vTabSelectViews[i] == null) {
      Log.w(TAG,"setTabSelection  vTabSelectViews[" + i + "] == null >> continue;");
      continue;
    }
    for (int j=0; j < vTabSelectViews[i].length; j++) {
      vTabSelectViews[i][j].setSelected(j == position);
    }
  }
}
