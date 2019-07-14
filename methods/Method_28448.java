private void measureHorizontal(int widthMeasureSpec,int heightMeasureSpec){
  int wSize=MeasureSpec.getSize(widthMeasureSpec) - (getPaddingLeft() + getPaddingRight());
  if (MeasureSpec.getMode(widthMeasureSpec) == MeasureSpec.UNSPECIFIED)   wSize=Integer.MAX_VALUE;
  int count=getChildCount();
  int rowWidth=0;
  int totalHeight=0;
  int rowMaxHeight=0;
  int childWidth;
  int childHeight;
  int maxRowWidth=getPaddingLeft() + getPaddingRight();
  for (int i=0; i < count; i++) {
    final View child=getChildAt(i);
    if (child.getVisibility() != GONE) {
      measureChildWithMargins(child,widthMeasureSpec,0,heightMeasureSpec,0);
      final LayoutParams lp=(LayoutParams)child.getLayoutParams();
      childWidth=child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
      childHeight=child.getMeasuredHeight() + lp.topMargin + lp.bottomMargin;
      rowMaxHeight=Math.max(rowMaxHeight,childHeight);
      if (childWidth + rowWidth > wSize) {
        totalHeight+=rowMaxHeight;
        maxRowWidth=Math.max(maxRowWidth,rowWidth);
        rowWidth=childWidth;
        rowMaxHeight=childHeight;
      }
 else {
        rowWidth+=childWidth;
      }
    }
  }
  if (rowWidth != 0) {
    maxRowWidth=Math.max(maxRowWidth,rowWidth);
    totalHeight+=rowMaxHeight;
  }
  if (MeasureSpec.getMode(widthMeasureSpec) == MeasureSpec.UNSPECIFIED)   wSize=maxRowWidth + (getPaddingLeft() + getPaddingRight());
  setMeasuredDimension(resolveSize(wSize,widthMeasureSpec),resolveSize(totalHeight + getPaddingTop() + getPaddingBottom(),heightMeasureSpec));
}
