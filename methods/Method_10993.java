@Override protected void itemCollapseAnimatorSet(RxCardStackView.ViewHolder viewHolder){
  int childTop=mRxCardStackView.getPaddingTop();
  for (int i=0; i < mRxCardStackView.getChildCount(); i++) {
    View child=mRxCardStackView.getChildAt(i);
    child.clearAnimation();
    final RxCardStackView.LayoutParams lp=(RxCardStackView.LayoutParams)child.getLayoutParams();
    childTop+=lp.topMargin;
    if (i != 0) {
      childTop-=mRxCardStackView.getOverlapGaps() * 2;
      ObjectAnimator oAnim=ObjectAnimator.ofFloat(child,View.Y,child.getY(),childTop);
      mSet.play(oAnim);
    }
 else {
      ObjectAnimator oAnim=ObjectAnimator.ofFloat(child,View.Y,child.getY(),childTop);
      mSet.play(oAnim);
    }
    childTop+=lp.mHeaderHeight;
  }
}
