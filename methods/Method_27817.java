@Override public void onWorkOffline(@NonNull String login){
  if (users.isEmpty()) {
    manageDisposable(RxHelper.getSingle(User.getUserFollowerList(login)).subscribe(userModels -> sendToView(view -> view.onNotifyAdapter(userModels,1))));
  }
 else {
    sendToView(ProfileFollowersMvp.View::hideProgress);
  }
}
