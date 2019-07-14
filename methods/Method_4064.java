private void measureChildWithDecorationsAndMargin(View child,int widthSpec,int heightSpec,boolean alreadyMeasured){
  calculateItemDecorationsForChild(child,mTmpRect);
  LayoutParams lp=(LayoutParams)child.getLayoutParams();
  widthSpec=updateSpecWithExtra(widthSpec,lp.leftMargin + mTmpRect.left,lp.rightMargin + mTmpRect.right);
  heightSpec=updateSpecWithExtra(heightSpec,lp.topMargin + mTmpRect.top,lp.bottomMargin + mTmpRect.bottom);
  final boolean measure=alreadyMeasured ? shouldReMeasureChild(child,widthSpec,heightSpec,lp) : shouldMeasureChild(child,widthSpec,heightSpec,lp);
  if (measure) {
    child.measure(widthSpec,heightSpec);
  }
}
