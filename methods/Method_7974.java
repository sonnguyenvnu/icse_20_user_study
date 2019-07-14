private int getMaxNameWidth(){
  if (documentAttachType == DOCUMENT_ATTACH_TYPE_STICKER || documentAttachType == DOCUMENT_ATTACH_TYPE_WALLPAPER || currentMessageObject.type == MessageObject.TYPE_ROUND_VIDEO) {
    int maxWidth;
    if (AndroidUtilities.isTablet()) {
      if (isChat && !currentMessageObject.isOutOwner() && currentMessageObject.needDrawAvatar()) {
        maxWidth=AndroidUtilities.getMinTabletSide() - AndroidUtilities.dp(42);
      }
 else {
        maxWidth=AndroidUtilities.getMinTabletSide();
      }
    }
 else {
      if (isChat && !currentMessageObject.isOutOwner() && currentMessageObject.needDrawAvatar()) {
        maxWidth=Math.min(AndroidUtilities.displaySize.x,AndroidUtilities.displaySize.y) - AndroidUtilities.dp(42);
      }
 else {
        maxWidth=Math.min(AndroidUtilities.displaySize.x,AndroidUtilities.displaySize.y);
      }
    }
    return maxWidth - backgroundWidth - AndroidUtilities.dp(57);
  }
  if (currentMessagesGroup != null) {
    int dWidth;
    if (AndroidUtilities.isTablet()) {
      dWidth=AndroidUtilities.getMinTabletSide();
    }
 else {
      dWidth=AndroidUtilities.displaySize.x;
    }
    int firstLineWidth=0;
    for (int a=0; a < currentMessagesGroup.posArray.size(); a++) {
      MessageObject.GroupedMessagePosition position=currentMessagesGroup.posArray.get(a);
      if (position.minY == 0) {
        firstLineWidth+=Math.ceil((position.pw + position.leftSpanOffset) / 1000.0f * dWidth);
      }
 else {
        break;
      }
    }
    return firstLineWidth - AndroidUtilities.dp(31 + (isAvatarVisible ? 48 : 0));
  }
 else {
    return backgroundWidth - AndroidUtilities.dp(mediaBackground ? 22 : 31);
  }
}
