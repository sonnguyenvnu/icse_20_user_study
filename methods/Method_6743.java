public int getMaxMessageTextWidth(){
  int maxWidth=0;
  if (AndroidUtilities.isTablet() && eventId != 0) {
    generatedWithMinSize=AndroidUtilities.dp(530);
  }
 else {
    generatedWithMinSize=AndroidUtilities.isTablet() ? AndroidUtilities.getMinTabletSide() : AndroidUtilities.displaySize.x;
  }
  generatedWithDensity=AndroidUtilities.density;
  if (messageOwner.media instanceof TLRPC.TL_messageMediaWebPage && messageOwner.media.webpage != null && "telegram_background".equals(messageOwner.media.webpage.type)) {
    try {
      Uri uri=Uri.parse(messageOwner.media.webpage.url);
      if (uri.getQueryParameter("bg_color") != null) {
        maxWidth=AndroidUtilities.dp(220);
      }
 else       if (uri.getLastPathSegment().length() == 6) {
        maxWidth=AndroidUtilities.dp(200);
      }
    }
 catch (    Exception ignore) {
    }
  }
  if (maxWidth == 0) {
    maxWidth=generatedWithMinSize - AndroidUtilities.dp(needDrawAvatarInternal() && !isOutOwner() ? 132 : 80);
    if (needDrawShareButton() && !isOutOwner()) {
      maxWidth-=AndroidUtilities.dp(10);
    }
    if (messageOwner.media instanceof TLRPC.TL_messageMediaGame) {
      maxWidth-=AndroidUtilities.dp(10);
    }
  }
  return maxWidth;
}
