public void login(String email,String password){
  setIsLoading(true);
  getCompositeDisposable().add(getDataManager().doServerLoginApiCall(new LoginRequest.ServerLoginRequest(email,password)).doOnSuccess(response -> getDataManager().updateUserInfo(response.getAccessToken(),response.getUserId(),DataManager.LoggedInMode.LOGGED_IN_MODE_SERVER,response.getUserName(),response.getUserEmail(),response.getGoogleProfilePicUrl())).subscribeOn(getSchedulerProvider().io()).observeOn(getSchedulerProvider().ui()).subscribe(response -> {
    setIsLoading(false);
    getNavigator().openMainActivity();
  }
,throwable -> {
    setIsLoading(false);
    getNavigator().handleError(throwable);
  }
));
}
