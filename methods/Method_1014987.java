private void init(){
  userViewModel=WfcUIKit.getAppScopeViewModel(UserViewModel.class);
  userViewModel.getUserInfoAsync(userViewModel.getUserId(),true).observe(this,info -> {
    userInfo=info;
    if (userInfo != null) {
      updateUserInfo(userInfo);
    }
  }
);
  userViewModel.userInfoLiveData().observeForever(userInfoLiveDataObserver);
}
