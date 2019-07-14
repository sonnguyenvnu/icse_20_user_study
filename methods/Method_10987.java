private void doCardClickAnimation(final ViewHolder viewHolder,int position){
  checkContentHeightByParent();
  mRxAdapterAnimator.itemClick(viewHolder,position);
}
