protected int getCollapseStartTop(int collapseShowItemCount){
  return mRxCardStackView.getOverlapGapsCollapse() * (mRxCardStackView.getNumBottomShow() - collapseShowItemCount - (mRxCardStackView.getNumBottomShow() - (mRxCardStackView.getChildCount() - mRxCardStackView.getSelectPosition() > mRxCardStackView.getNumBottomShow() ? mRxCardStackView.getNumBottomShow() : mRxCardStackView.getChildCount() - mRxCardStackView.getSelectPosition() - 1)));
}
