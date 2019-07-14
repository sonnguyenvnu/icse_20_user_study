/** 
 * ???? 
 */
public void notifyDataSetChanged(){
  mTabsContainer.removeAllViews();
  this.mTabCount=mTabEntitys.size();
  View tabView;
  for (int i=0; i < mTabCount; i++) {
    if (mIconGravity == Gravity.LEFT) {
      tabView=View.inflate(mContext,R.layout.layout_tab_left,null);
    }
 else     if (mIconGravity == Gravity.RIGHT) {
      tabView=View.inflate(mContext,R.layout.layout_tab_right,null);
    }
 else     if (mIconGravity == Gravity.BOTTOM) {
      tabView=View.inflate(mContext,R.layout.layout_tab_bottom,null);
    }
 else {
      tabView=View.inflate(mContext,R.layout.layout_tab_top,null);
    }
    tabView.setTag(i);
    addTab(i,tabView);
  }
  updateTabStyles();
}
