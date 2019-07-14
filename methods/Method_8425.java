public void setAvatarType(int value){
  avatarType=value;
  if (avatarType == AVATAR_TYPE_ARCHIVED) {
    color=Theme.getColor(Theme.key_avatar_backgroundArchivedHidden);
  }
 else {
    color=Theme.getColor(Theme.key_avatar_backgroundSaved);
  }
}
