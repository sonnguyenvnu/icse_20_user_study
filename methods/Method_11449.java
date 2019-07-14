/** 
 * Calculates control height and creates text layouts
 * @param heightSize the input layout height
 * @param mode       the layout mode
 * @return the calculated control height
 */
private int calculateLayoutHeight(int heightSize,int mode){
  mItemsLayout.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
  mItemsLayout.measure(MeasureSpec.makeMeasureSpec(0,MeasureSpec.UNSPECIFIED),MeasureSpec.makeMeasureSpec(heightSize,MeasureSpec.UNSPECIFIED));
  int height=mItemsLayout.getMeasuredHeight();
  if (mode == MeasureSpec.EXACTLY) {
    height=heightSize;
  }
 else {
    height+=2 * mItemsPadding;
    height=Math.max(height,getSuggestedMinimumHeight());
    if (mode == MeasureSpec.AT_MOST && heightSize < height) {
      height=heightSize;
    }
  }
  mItemsLayout.measure(MeasureSpec.makeMeasureSpec(400,MeasureSpec.EXACTLY),MeasureSpec.makeMeasureSpec(height - 2 * mItemsPadding,MeasureSpec.EXACTLY));
  return height;
}
