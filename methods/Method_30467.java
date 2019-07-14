private void bindRecentUser(ImageView avatarImage,final Account account){
  if (account == null) {
    avatarImage.setVisibility(GONE);
    return;
  }
  avatarImage.setVisibility(VISIBLE);
  User user=mAdapter.getUser(account);
  if (user != null) {
    bindAvatarImage(avatarImage,user.getLargeAvatarOrAvatar());
  }
 else {
    bindAvatarImage(avatarImage,null);
  }
  avatarImage.setOnClickListener(new OnClickListener(){
    @Override public void onClick(    View view){
      switchToAccountWithTransitionIfNotRunning(account);
    }
  }
);
  avatarImage.setOnLongClickListener(new OnLongClickListener(){
    @Override public boolean onLongClick(    View view){
      if (mAccountTransitionRunning) {
        return false;
      }
      if (mListener != null) {
        mListener.openProfile(account);
      }
      return true;
    }
  }
);
}
