@OnClick(R.id.userInformation) void onOpenAvatar(){
  if (userModel != null)   ActivityHelper.startCustomTab(getActivity(),userModel.getAvatarUrl());
}
