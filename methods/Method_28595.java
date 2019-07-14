/** 
 * ?????????????position setPageIndicator????
 * @return
 */
public ConvenientBanner setFirstItemPos(int position){
  cbLoopScaleHelper.setFirstItemPos(canLoop ? mDatas.size() + position : position);
  return this;
}
