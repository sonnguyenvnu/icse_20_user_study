private void layoutChild(){
  int childTop=getPaddingTop();
  int childLeft=getPaddingLeft();
  for (int i=0; i < getChildCount(); i++) {
    View child=getChildAt(i);
    final int childWidth=child.getMeasuredWidth();
    int childHeight=child.getMeasuredHeight();
    final LayoutParams lp=(LayoutParams)child.getLayoutParams();
    childTop+=lp.topMargin;
    if (i != 0) {
      childTop-=mOverlapGaps * 2;
      child.layout(childLeft,childTop,childLeft + childWidth,childTop + childHeight);
    }
 else {
      child.layout(childLeft,childTop,childLeft + childWidth,childTop + childHeight);
    }
    childTop+=lp.mHeaderHeight;
  }
}
