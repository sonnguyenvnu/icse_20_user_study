private void measureChildWithDecorationsAndMargin(View child,LayoutParams lp,boolean alreadyMeasured){
  if (lp.mFullSpan) {
    if (mOrientation == VERTICAL) {
      measureChildWithDecorationsAndMargin(child,mFullSizeSpec,getChildMeasureSpec(getHeight(),getHeightMode(),getPaddingTop() + getPaddingBottom(),lp.height,true),alreadyMeasured);
    }
 else {
      measureChildWithDecorationsAndMargin(child,getChildMeasureSpec(getWidth(),getWidthMode(),getPaddingLeft() + getPaddingRight(),lp.width,true),mFullSizeSpec,alreadyMeasured);
    }
  }
 else {
    if (mOrientation == VERTICAL) {
      measureChildWithDecorationsAndMargin(child,getChildMeasureSpec(mSizePerSpan,getWidthMode(),0,lp.width,false),getChildMeasureSpec(getHeight(),getHeightMode(),getPaddingTop() + getPaddingBottom(),lp.height,true),alreadyMeasured);
    }
 else {
      measureChildWithDecorationsAndMargin(child,getChildMeasureSpec(getWidth(),getWidthMode(),getPaddingLeft() + getPaddingRight(),lp.width,true),getChildMeasureSpec(mSizePerSpan,getHeightMode(),0,lp.height,false),alreadyMeasured);
    }
  }
}
