@Subscribe(threadMode=ThreadMode.POSTING) public void onUserUpdated(UserUpdatedEvent event){
  if (event.isFromMyself(this)) {
    return;
  }
  if (event.mUser.isIdOrUid(mUserIdOrUid)) {
    mUserIdOrUid=event.mUser.getIdOrUid();
    mUser=event.mUser;
  }
}
