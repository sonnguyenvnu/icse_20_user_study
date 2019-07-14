@Override public void onUserResponse(@Nullable Login userModel){
  if (userModel != null) {
    manageObservable(Login.onMultipleLogin(userModel,isEnterprise(),true).doOnComplete(() -> sendToView(view -> view.onSuccessfullyLoggedIn(isEnterprise()))));
    return;
  }
  sendToView(view -> view.showMessage(R.string.error,R.string.failed_login));
}
