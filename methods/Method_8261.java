private void checkActionBarMenu(){
  if (currentEncryptedChat != null && !(currentEncryptedChat instanceof TLRPC.TL_encryptedChat) || currentChat != null && ChatObject.isNotInChat(currentChat) || currentUser != null && UserObject.isDeleted(currentUser)) {
    if (timeItem2 != null) {
      timeItem2.setVisibility(View.GONE);
    }
    if (avatarContainer != null) {
      avatarContainer.hideTimeItem();
    }
  }
 else {
    if (timeItem2 != null) {
      timeItem2.setVisibility(View.VISIBLE);
    }
    if (avatarContainer != null) {
      avatarContainer.showTimeItem();
    }
  }
  if (avatarContainer != null && currentEncryptedChat != null) {
    avatarContainer.setTime(currentEncryptedChat.ttl);
  }
  checkAndUpdateAvatar();
}
