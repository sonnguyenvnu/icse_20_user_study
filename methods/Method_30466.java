private void bindRecentUsers(){
  mRecentOneAccount=AccountUtils.getRecentOneAccount();
  bindRecentUser(mRecentOneAvatarImage,mRecentOneAccount);
  mRecentTwoAccount=AccountUtils.getRecentTwoAccount();
  bindRecentUser(mRecentTwoAvatarImage,mRecentTwoAccount);
}
