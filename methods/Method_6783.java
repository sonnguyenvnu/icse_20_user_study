public int getApproximateHeight(){
  if (type == 0) {
    int height=textHeight + (messageOwner.media instanceof TLRPC.TL_messageMediaWebPage && messageOwner.media.webpage instanceof TLRPC.TL_webPage ? AndroidUtilities.dp(100) : 0);
    if (isReply()) {
      height+=AndroidUtilities.dp(42);
    }
    return height;
  }
 else   if (type == 2) {
    return AndroidUtilities.dp(72);
  }
 else   if (type == 12) {
    return AndroidUtilities.dp(71);
  }
 else   if (type == 9) {
    return AndroidUtilities.dp(100);
  }
 else   if (type == 4) {
    return AndroidUtilities.dp(114);
  }
 else   if (type == 14) {
    return AndroidUtilities.dp(82);
  }
 else   if (type == 10) {
    return AndroidUtilities.dp(30);
  }
 else   if (type == 11) {
    return AndroidUtilities.dp(50);
  }
 else   if (type == TYPE_ROUND_VIDEO) {
    return AndroidUtilities.roundMessageSize;
  }
 else   if (type == TYPE_STICKER || type == TYPE_ANIMATED_STICKER) {
    float maxHeight=AndroidUtilities.displaySize.y * 0.4f;
    float maxWidth;
    if (AndroidUtilities.isTablet()) {
      maxWidth=AndroidUtilities.getMinTabletSide() * 0.5f;
    }
 else {
      maxWidth=AndroidUtilities.displaySize.x * 0.5f;
    }
    int photoHeight=0;
    int photoWidth=0;
    for (    TLRPC.DocumentAttribute attribute : messageOwner.media.document.attributes) {
      if (attribute instanceof TLRPC.TL_documentAttributeImageSize) {
        photoWidth=attribute.w;
        photoHeight=attribute.h;
        break;
      }
    }
    if (photoWidth == 0) {
      photoHeight=(int)maxHeight;
      photoWidth=photoHeight + AndroidUtilities.dp(100);
    }
    if (photoHeight > maxHeight) {
      photoWidth*=maxHeight / photoHeight;
      photoHeight=(int)maxHeight;
    }
    if (photoWidth > maxWidth) {
      photoHeight*=maxWidth / photoWidth;
    }
    return photoHeight + AndroidUtilities.dp(14);
  }
 else {
    int photoHeight;
    int photoWidth;
    if (AndroidUtilities.isTablet()) {
      photoWidth=(int)(AndroidUtilities.getMinTabletSide() * 0.7f);
    }
 else {
      photoWidth=(int)(Math.min(AndroidUtilities.displaySize.x,AndroidUtilities.displaySize.y) * 0.7f);
    }
    photoHeight=photoWidth + AndroidUtilities.dp(100);
    if (photoWidth > AndroidUtilities.getPhotoSize()) {
      photoWidth=AndroidUtilities.getPhotoSize();
    }
    if (photoHeight > AndroidUtilities.getPhotoSize()) {
      photoHeight=AndroidUtilities.getPhotoSize();
    }
    TLRPC.PhotoSize currentPhotoObject=FileLoader.getClosestPhotoSizeWithSize(photoThumbs,AndroidUtilities.getPhotoSize());
    if (currentPhotoObject != null) {
      float scale=(float)currentPhotoObject.w / (float)photoWidth;
      int h=(int)(currentPhotoObject.h / scale);
      if (h == 0) {
        h=AndroidUtilities.dp(100);
      }
      if (h > photoHeight) {
        h=photoHeight;
      }
 else       if (h < AndroidUtilities.dp(120)) {
        h=AndroidUtilities.dp(120);
      }
      if (needDrawBluredPreview()) {
        if (AndroidUtilities.isTablet()) {
          h=(int)(AndroidUtilities.getMinTabletSide() * 0.5f);
        }
 else {
          h=(int)(Math.min(AndroidUtilities.displaySize.x,AndroidUtilities.displaySize.y) * 0.5f);
        }
      }
      photoHeight=h;
    }
    return photoHeight + AndroidUtilities.dp(14);
  }
}
