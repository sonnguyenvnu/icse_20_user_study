/** 
 * Set indicator measure
 * @param indicator indicator view
 * @param width     indicator width
 * @param height    indicator height
 * @param margin    indicator top margin
 */
private void setIndicatorMeasure(View indicator,int width,int height,int margin){
  if (indicator != null) {
    ViewGroup.LayoutParams layoutParams=indicator.getLayoutParams();
    layoutParams.width=width;
    layoutParams.height=height;
    if (margin > 0) {
      if (layoutParams instanceof FrameLayout.LayoutParams) {
        FrameLayout.LayoutParams frameLayoutParams=(FrameLayout.LayoutParams)layoutParams;
        frameLayoutParams.topMargin=margin;
      }
 else       if (layoutParams instanceof LinearLayout.LayoutParams) {
        LinearLayout.LayoutParams linearLayoutParams=(LinearLayout.LayoutParams)layoutParams;
        linearLayoutParams.topMargin=margin;
      }
    }
    indicator.setLayoutParams(layoutParams);
  }
}
