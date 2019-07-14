public int getForwardNameCenterX(){
  if (currentUser != null && currentUser.id == 0) {
    return (int)avatarImage.getCenterX();
  }
  return forwardNameX + forwardNameCenterX;
}
