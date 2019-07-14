private void onItemExpand(final RxCardStackView.ViewHolder viewHolder,int position){
  final int preSelectPosition=mRxCardStackView.getSelectPosition();
  final RxCardStackView.ViewHolder preSelectViewHolder=mRxCardStackView.getViewHolder(preSelectPosition);
  if (preSelectViewHolder != null) {
    preSelectViewHolder.onItemExpand(false);
  }
  mRxCardStackView.setSelectPosition(position);
  itemExpandAnimatorSet(viewHolder,position);
  mSet.addListener(new AnimatorListenerAdapter(){
    @Override public void onAnimationStart(    Animator animation){
      super.onAnimationStart(animation);
      mRxCardStackView.setScrollEnable(false);
      if (preSelectViewHolder != null) {
        preSelectViewHolder.onAnimationStateChange(RxCardStackView.ANIMATION_STATE_START,false);
      }
      viewHolder.onAnimationStateChange(RxCardStackView.ANIMATION_STATE_START,true);
    }
    @Override public void onAnimationEnd(    Animator animation){
      super.onAnimationEnd(animation);
      viewHolder.onItemExpand(true);
      if (preSelectViewHolder != null) {
        preSelectViewHolder.onAnimationStateChange(RxCardStackView.ANIMATION_STATE_END,false);
      }
      viewHolder.onAnimationStateChange(RxCardStackView.ANIMATION_STATE_END,true);
    }
    @Override public void onAnimationCancel(    Animator animation){
      super.onAnimationCancel(animation);
      if (preSelectViewHolder != null) {
        preSelectViewHolder.onAnimationStateChange(RxCardStackView.ANIMATION_STATE_CANCEL,false);
      }
      viewHolder.onAnimationStateChange(RxCardStackView.ANIMATION_STATE_CANCEL,true);
    }
  }
);
  mSet.start();
}
