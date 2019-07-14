private void measureVertical(int widthMeasureSpec,int heightMeasureSpec){
  int hSize=MeasureSpec.getSize(heightMeasureSpec) - (getPaddingTop() + getPaddingBottom());
  int count=getChildCount();
  int columnHeight=0;
  int totalWidth=0, maxColumnHeight=0;
  int columnMaxWidth=0;
  int childWidth;
  int childHeight;
  if (MeasureSpec.getMode(heightMeasureSpec) == MeasureSpec.UNSPECIFIED)   hSize=Integer.MAX_VALUE;
  for (int i=0; i < count; i++) {
    final View child=getChildAt(i);
    if (child.getVisibility() != GONE) {
      measureChildWithMargins(child,widthMeasureSpec,0,heightMeasureSpec,0);
      final LayoutParams lp=(LayoutParams)child.getLayoutParams();
      childWidth=child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
      childHeight=child.getMeasuredHeight() + lp.topMargin + lp.bottomMargin;
      columnMaxWidth=Math.max(columnMaxWidth,childWidth);
      if (childHeight + columnHeight > hSize) {
        totalWidth+=columnMaxWidth;
        maxColumnHeight=Math.max(maxColumnHeight,columnHeight);
        columnHeight=childHeight;
        columnMaxWidth=childWidth;
      }
 else {
        columnHeight+=childHeight;
      }
    }
  }
  if (columnHeight != 0) {
    maxColumnHeight=Math.max(maxColumnHeight,columnHeight);
    totalWidth+=columnMaxWidth;
  }
  if (MeasureSpec.getMode(heightMeasureSpec) == MeasureSpec.UNSPECIFIED)   hSize=maxColumnHeight + (getPaddingTop() + getPaddingBottom());
  setMeasuredDimension(resolveSize(totalWidth + getPaddingRight() + getPaddingLeft(),widthMeasureSpec),resolveSize(hSize,heightMeasureSpec));
}
