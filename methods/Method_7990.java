public int getBackgroundDrawableLeft(){
  if (currentMessageObject.isOutOwner()) {
    return layoutWidth - backgroundWidth - (!mediaBackground ? 0 : AndroidUtilities.dp(9));
  }
 else {
    return AndroidUtilities.dp((isChat && isAvatarVisible ? 48 : 0) + (!mediaBackground ? 3 : 9));
  }
}
