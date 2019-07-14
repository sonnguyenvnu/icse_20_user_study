@Override public void onFollowButtonClicked(@NonNull String login){
  manageDisposable(RxHelper.getObservable(!isFollowing ? RestProvider.getUserService(isEnterprise()).followUser(login) : RestProvider.getUserService(isEnterprise()).unfollowUser(login)).subscribe(booleanResponse -> {
    if (booleanResponse.code() == 204) {
      isFollowing=!isFollowing;
      sendToView(ProfileOverviewMvp.View::invalidateFollowBtn);
    }
  }
,this::onError));
}
