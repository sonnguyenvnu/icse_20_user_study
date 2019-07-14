/** 
 * ??????
 * @param align ??????? ?RelativeLayout.ALIGN_PARENT_LEFT???? ?RelativeLayout.CENTER_HORIZONTAL???? ?RelativeLayout.ALIGN_PARENT_RIGHT?
 * @return
 */
public ConvenientBanner setPageIndicatorAlign(PageIndicatorAlign align){
  RelativeLayout.LayoutParams layoutParams=(RelativeLayout.LayoutParams)loPageTurningPoint.getLayoutParams();
  layoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT,align == PageIndicatorAlign.ALIGN_PARENT_LEFT ? RelativeLayout.TRUE : 0);
  layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT,align == PageIndicatorAlign.ALIGN_PARENT_RIGHT ? RelativeLayout.TRUE : 0);
  layoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL,align == PageIndicatorAlign.CENTER_HORIZONTAL ? RelativeLayout.TRUE : 0);
  loPageTurningPoint.setLayoutParams(layoutParams);
  return this;
}
