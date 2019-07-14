/** 
 * ??????
 */
public void notifyDataSetChanged(){
  viewPager.getAdapter().notifyDataSetChanged();
  if (page_indicatorId != null)   setPageIndicator(page_indicatorId);
  cbLoopScaleHelper.setCurrentItem(canLoop ? mDatas.size() : 0);
}
