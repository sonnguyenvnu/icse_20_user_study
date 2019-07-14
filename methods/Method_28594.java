/** 
 * ????????position
 * @return
 */
public ConvenientBanner setCurrentItem(int position,boolean smoothScroll){
  cbLoopScaleHelper.setCurrentItem(canLoop ? mDatas.size() + position : position,smoothScroll);
  return this;
}
