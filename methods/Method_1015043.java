private void init(){
  userViewModel=WfcUIKit.getAppScopeViewModel(UserViewModel.class);
  contactViewModel=WfcUIKit.getAppScopeViewModel(ContactViewModel.class);
  String selfUid=userViewModel.getUserId();
  if (selfUid.equals(userInfo.uid)) {
    chatButton.setVisibility(View.GONE);
    voipChatButton.setVisibility(View.GONE);
    inviteButton.setVisibility(View.GONE);
    qrCodeOptionItemView.setVisibility(View.VISIBLE);
    aliasOptionItemView.setVisibility(View.VISIBLE);
  }
 else   if (contactViewModel.isFriend(userInfo.uid)) {
    chatButton.setVisibility(View.VISIBLE);
    voipChatButton.setVisibility(View.VISIBLE);
    inviteButton.setVisibility(View.GONE);
  }
 else {
    chatButton.setVisibility(View.GONE);
    voipChatButton.setVisibility(View.GONE);
    inviteButton.setVisibility(View.VISIBLE);
    aliasOptionItemView.setVisibility(View.GONE);
  }
  setUserInfo(userInfo);
  userViewModel.userInfoLiveData().observe(this,userInfos -> {
    for (    UserInfo info : userInfos) {
      if (userInfo.uid.equals(info.uid)) {
        userInfo=info;
        setUserInfo(info);
        break;
      }
    }
  }
);
  userViewModel.getUserInfo(userInfo.uid,true);
}
