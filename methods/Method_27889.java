@Override public void onCheckFollowStatus(@NonNull String login){
  if (!TextUtils.equals(login,Login.getUser().getLogin())) {
    manageDisposable(RxHelper.getObservable(RestProvider.getUserService(isEnterprise()).getFollowStatus(login)).subscribe(booleanResponse -> {
      isSuccessResponse=true;
      isFollowing=booleanResponse.code() == 204;
      sendToView(ProfileOverviewMvp.View::invalidateFollowBtn);
    }
,Throwable::printStackTrace));
  }
}
