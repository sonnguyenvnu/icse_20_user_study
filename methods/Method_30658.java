@Override public void onFollowUser(User user,boolean follow){
  if (follow) {
    FollowUserManager.getInstance().write(user,true,getActivity());
  }
 else {
    ConfirmUnfollowUserDialogFragment.show(this);
  }
}
