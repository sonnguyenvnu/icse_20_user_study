public void setState(int state){
  if (mState == state) {
    return;
  }
  mState=state;
  if (mHasItem) {
    if (mViewHolder != null) {
      onBindViewHolder(mViewHolder,0);
    }
 else {
      notifyItemChanged(0);
    }
  }
}
