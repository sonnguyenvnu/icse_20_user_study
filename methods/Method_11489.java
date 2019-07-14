/** 
 * ???? 
 */
public void notifyDataSetChanged(){
  mTabsContainer.removeAllViews();
  this.mTabCount=mTitles.length;
  View tabView;
  for (int i=0; i < mTabCount; i++) {
    tabView=View.inflate(mContext,R.layout.layout_tab_segment,null);
    tabView.setTag(i);
    addTab(i,tabView);
  }
  updateTabStyles();
}
