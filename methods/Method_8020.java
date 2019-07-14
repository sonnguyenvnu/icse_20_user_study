public void animateArchiveAvatar(){
  if (avatarDrawable.getAvatarType() != AvatarDrawable.AVATAR_TYPE_ARCHIVED) {
    return;
  }
  animatingArchiveAvatar=true;
  animatingArchiveAvatarProgress=0.0f;
  Theme.dialogs_archiveAvatarDrawable.setCallback(this);
  Theme.dialogs_archiveAvatarDrawable.setProgress(0.0f);
  Theme.dialogs_archiveAvatarDrawable.start();
  invalidate();
}
