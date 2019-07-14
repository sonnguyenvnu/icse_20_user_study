public void setTitleVisibility(boolean titleVisibility){
  mTitleVisibility=titleVisibility;
  if (mTitleVisibility) {
    mTvTitle.setVisibility(VISIBLE);
  }
 else {
    mTvTitle.setVisibility(GONE);
  }
}
