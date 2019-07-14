@SuppressLint("NewApi") @Override protected void onMeasure(int widthMeasureSpec,int heightMeasureSpec){
  int widthSize=MeasureSpec.getSize(widthMeasureSpec);
  int heightSize=MeasureSpec.getSize(heightMeasureSpec);
  setMeasuredDimension(widthSize,heightSize);
  if (Build.VERSION.SDK_INT < 21) {
    inLayout=true;
    if (heightSize == AndroidUtilities.displaySize.y + AndroidUtilities.statusBarHeight) {
      if (getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
        setPadding(0,AndroidUtilities.statusBarHeight,0,0);
      }
      heightSize=AndroidUtilities.displaySize.y;
    }
 else {
      if (getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
        setPadding(0,0,0,0);
      }
    }
    inLayout=false;
  }
  final boolean applyInsets=lastInsets != null && Build.VERSION.SDK_INT >= 21;
  final int childCount=getChildCount();
  for (int i=0; i < childCount; i++) {
    final View child=getChildAt(i);
    if (child.getVisibility() == GONE) {
      continue;
    }
    final LayoutParams lp=(LayoutParams)child.getLayoutParams();
    if (applyInsets) {
      if (child.getFitsSystemWindows()) {
        dispatchChildInsets(child,lastInsets,lp.gravity);
      }
 else       if (child.getTag() == null) {
        applyMarginInsets(lp,lastInsets,lp.gravity,Build.VERSION.SDK_INT >= 21);
      }
    }
    if (drawerLayout != child) {
      final int contentWidthSpec=MeasureSpec.makeMeasureSpec(widthSize - lp.leftMargin - lp.rightMargin,MeasureSpec.EXACTLY);
      final int contentHeightSpec=MeasureSpec.makeMeasureSpec(heightSize - lp.topMargin - lp.bottomMargin,MeasureSpec.EXACTLY);
      child.measure(contentWidthSpec,contentHeightSpec);
    }
 else {
      child.setPadding(0,0,0,0);
      final int drawerWidthSpec=getChildMeasureSpec(widthMeasureSpec,minDrawerMargin + lp.leftMargin + lp.rightMargin,lp.width);
      final int drawerHeightSpec=getChildMeasureSpec(heightMeasureSpec,lp.topMargin + lp.bottomMargin,lp.height);
      child.measure(drawerWidthSpec,drawerHeightSpec);
    }
  }
}
