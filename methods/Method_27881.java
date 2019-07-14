@Override public void invalidateFollowBtn(){
  hideProgress();
  if (isMeOrOrganization())   return;
  if (getPresenter().isSuccessResponse()) {
    followBtn.setEnabled(true);
    followBtn.setActivated(getPresenter().isFollowing());
    followBtn.setText(getPresenter().isFollowing() ? getString(R.string.unfollow) : getString(R.string.follow));
  }
}
