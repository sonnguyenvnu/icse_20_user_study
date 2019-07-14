/** 
 * ???? 
 */
public void notifyDataSetChanged(){
  mTabsContainer.removeAllViews();
  this.mTabCount=mTitles == null ? mViewPager.getAdapter().getCount() : mTitles.size();
  View tabView;
  for (int i=0; i < mTabCount; i++) {
    tabView=View.inflate(mContext,R.layout.layout_tab,null);
    CharSequence pageTitle=mTitles == null ? mViewPager.getAdapter().getPageTitle(i) : mTitles.get(i);
    addTab(i,pageTitle.toString(),tabView);
  }
  updateTabStyles();
}
