@Override public void onResponse(User response){
  ToastUtils.show(mFollow ? R.string.user_follow_successful : R.string.user_unfollow_successful,getContext());
  EventBusUtils.postAsync(new UserUpdatedEvent(response,this));
  stopSelf();
}
