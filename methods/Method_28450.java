/** 
 * Arranges the children in columns. Takes care about child margin, padding, gravity and child layout gravity.
 * @param left parent left
 * @param top parent top
 * @param right parent right
 * @param bottom parent bottom
 */
private void layoutVertical(int left,int top,int right,int bottom){
  final int count=getChildCount();
  if (count == 0)   return;
  final int width=right - getPaddingLeft() - left - getPaddingRight();
  final int height=bottom - getPaddingTop() - top - getPaddingBottom();
  int childTop=getPaddingTop();
  int childLeft=getPaddingLeft();
  int totalHorizontal=getPaddingLeft() + getPaddingRight();
  int totalVertical=0;
  int column=0;
  int maxChildWidth=0;
  for (int i=0; i < count; i++) {
    final View child=getChildAt(i);
    if (child != null && child.getVisibility() != View.GONE) {
      if (child.getMeasuredHeight() == 0 || child.getMeasuredWidth() == 0)       child.measure(MeasureSpec.makeMeasureSpec(width,MeasureSpec.AT_MOST),MeasureSpec.makeMeasureSpec(height,MeasureSpec.AT_MOST));
      LayoutParams lp=(LayoutParams)child.getLayoutParams();
      final int childWidth=child.getMeasuredWidth();
      final int childHeight=child.getMeasuredHeight();
      if (childTop + childHeight + lp.topMargin + lp.bottomMargin > height + getPaddingTop()) {
        updateChildPositionVertical(height,totalVertical,column,maxChildWidth);
        childTop=getPaddingTop();
        childLeft+=maxChildWidth;
        maxChildWidth=0;
        column++;
        totalVertical=0;
      }
      childTop+=lp.topMargin;
      mListPositions.add(new ViewPosition(childLeft,childTop,column));
      int currentWidth=childWidth + lp.leftMargin + lp.rightMargin;
      if (maxChildWidth < currentWidth)       maxChildWidth=currentWidth;
      childTop+=childHeight + lp.bottomMargin;
      totalVertical+=childHeight + lp.topMargin + lp.bottomMargin;
    }
  }
  updateChildPositionVertical(height,totalVertical,column,maxChildWidth);
  totalHorizontal+=childLeft + maxChildWidth;
  updateChildPositionHorizontal(width,totalHorizontal,column,0);
}
