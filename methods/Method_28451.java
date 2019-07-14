/** 
 * Arranges the children in rows. Takes care about child margin, padding, gravity and child layout gravity. Analog to vertical.
 * @param left parent left
 * @param top parent top
 * @param right parent right
 * @param bottom parent bottom
 */
private void layoutHorizontal(int left,int top,int right,int bottom){
  final int count=getChildCount();
  if (count == 0)   return;
  final int width=right - getPaddingLeft() - left - getPaddingRight();
  final int height=bottom - getPaddingTop() - top - getPaddingBottom();
  int childTop=getPaddingTop();
  int childLeft=getPaddingLeft();
  int totalHorizontal=0;
  int totalVertical=getPaddingTop() + getPaddingBottom();
  int row=0;
  int maxChildHeight=0;
  for (int i=0; i < count; i++) {
    final View child=getChildAt(i);
    if (child != null && child.getVisibility() != View.GONE) {
      if (child.getMeasuredHeight() == 0 || child.getMeasuredWidth() == 0)       child.measure(MeasureSpec.makeMeasureSpec(width,MeasureSpec.AT_MOST),MeasureSpec.makeMeasureSpec(height,MeasureSpec.AT_MOST));
      LayoutParams lp=(LayoutParams)child.getLayoutParams();
      final int childWidth=child.getMeasuredWidth();
      final int childHeight=child.getMeasuredHeight();
      if (childLeft + childWidth + lp.leftMargin + lp.rightMargin > width + getPaddingLeft()) {
        updateChildPositionHorizontal(width,totalHorizontal,row,maxChildHeight);
        childLeft=getPaddingLeft();
        childTop+=maxChildHeight;
        maxChildHeight=0;
        row++;
        totalHorizontal=0;
      }
      childLeft+=lp.leftMargin;
      mListPositions.add(new ViewPosition(childLeft,childTop,row));
      int currentHeight=childHeight + lp.topMargin + lp.bottomMargin;
      if (maxChildHeight < currentHeight)       maxChildHeight=currentHeight;
      childLeft+=childWidth + lp.rightMargin;
      totalHorizontal+=childWidth + lp.rightMargin + lp.leftMargin;
    }
  }
  updateChildPositionHorizontal(width,totalHorizontal,row,maxChildHeight);
  totalVertical+=childTop + maxChildHeight;
  updateChildPositionVertical(height,totalVertical,row,0);
}
