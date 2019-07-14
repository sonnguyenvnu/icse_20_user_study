@Override protected void itemCollapseAnimatorSet(RxCardStackView.ViewHolder viewHolder){
  int childTop=mRxCardStackView.getPaddingTop();
  for (int i=0; i < mRxCardStackView.getChildCount(); i++) {
    View child=mRxCardStackView.getChildAt(i);
    child.clearAnimation();
    final RxCardStackView.LayoutParams lp=(RxCardStackView.LayoutParams)child.getLayoutParams();
    childTop+=lp.topMargin;
    if (i != 0) {
      childTop-=mRxCardStackView.getOverlapGaps() * 2;
    }
    ObjectAnimator oAnim=ObjectAnimator.ofFloat(child,View.Y,child.getY(),childTop - mRxCardStackView.getRxScrollDelegate().getViewScrollY() < mRxCardStackView.getChildAt(0).getY() ? mRxCardStackView.getChildAt(0).getY() : childTop - mRxCardStackView.getRxScrollDelegate().getViewScrollY());
    mSet.play(oAnim);
    childTop+=lp.mHeaderHeight;
  }
}
