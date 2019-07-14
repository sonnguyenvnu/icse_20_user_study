/** 
 * Calculates control width
 * @param widthSize the input layout width
 * @param mode      the layout mode
 * @return the calculated control width
 */
private int calculateLayoutWidth(int widthSize,int mode){
  mItemsLayout.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
  mItemsLayout.measure(MeasureSpec.makeMeasureSpec(widthSize,MeasureSpec.UNSPECIFIED),MeasureSpec.makeMeasureSpec(0,MeasureSpec.UNSPECIFIED));
  int width=mItemsLayout.getMeasuredWidth();
  if (mode == MeasureSpec.EXACTLY) {
    width=widthSize;
  }
 else {
    width+=2 * mItemsPadding;
    width=Math.max(width,getSuggestedMinimumWidth());
    if (mode == MeasureSpec.AT_MOST && widthSize < width) {
      width=widthSize;
    }
  }
  mItemsLayout.measure(MeasureSpec.makeMeasureSpec(width - 2 * mItemsPadding,MeasureSpec.EXACTLY),MeasureSpec.makeMeasureSpec(0,MeasureSpec.UNSPECIFIED));
  return width;
}
