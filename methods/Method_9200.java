public void open(TLRPC.Document document,TLRPC.BotInlineResult botInlineResult,int contentType,boolean isRecent){
  if (parentActivity == null || windowView == null) {
    return;
  }
  stickerEmojiLayout=null;
  if (contentType == CONTENT_TYPE_STICKER) {
    if (document == null) {
      return;
    }
    if (textPaint == null) {
      textPaint=new TextPaint(Paint.ANTI_ALIAS_FLAG);
      textPaint.setTextSize(AndroidUtilities.dp(24));
    }
    TLRPC.InputStickerSet newSet=null;
    for (int a=0; a < document.attributes.size(); a++) {
      TLRPC.DocumentAttribute attribute=document.attributes.get(a);
      if (attribute instanceof TLRPC.TL_documentAttributeSticker && attribute.stickerset != null) {
        newSet=attribute.stickerset;
        break;
      }
    }
    if (newSet != null) {
      try {
        if (visibleDialog != null) {
          visibleDialog.setOnDismissListener(null);
          visibleDialog.dismiss();
          visibleDialog=null;
        }
      }
 catch (      Exception e) {
        FileLog.e(e);
      }
      AndroidUtilities.cancelRunOnUIThread(showSheetRunnable);
      AndroidUtilities.runOnUIThread(showSheetRunnable,1300);
    }
    currentStickerSet=newSet;
    TLRPC.PhotoSize thumb=FileLoader.getClosestPhotoSizeWithSize(document.thumbs,90);
    centerImage.setImage(ImageLocation.getForDocument(document),null,ImageLocation.getForDocument(thumb,document),null,"webp",currentStickerSet,1);
    for (int a=0; a < document.attributes.size(); a++) {
      TLRPC.DocumentAttribute attribute=document.attributes.get(a);
      if (attribute instanceof TLRPC.TL_documentAttributeSticker) {
        if (!TextUtils.isEmpty(attribute.alt)) {
          CharSequence emoji=Emoji.replaceEmoji(attribute.alt,textPaint.getFontMetricsInt(),AndroidUtilities.dp(24),false);
          stickerEmojiLayout=new StaticLayout(emoji,textPaint,AndroidUtilities.dp(100),Layout.Alignment.ALIGN_CENTER,1.0f,0.0f,false);
          break;
        }
      }
    }
  }
 else {
    if (document != null) {
      TLRPC.PhotoSize thumb=FileLoader.getClosestPhotoSizeWithSize(document.thumbs,90);
      centerImage.setImage(ImageLocation.getForDocument(document),null,ImageLocation.getForDocument(thumb,document),"90_90_b",document.size,null,"gif" + document,0);
    }
 else     if (botInlineResult != null) {
      if (botInlineResult.content == null) {
        return;
      }
      centerImage.setImage(ImageLocation.getForWebFile(WebFile.createWithWebDocument(botInlineResult.content)),null,ImageLocation.getForWebFile(WebFile.createWithWebDocument(botInlineResult.thumb)),"90_90_b",botInlineResult.content.size,null,"gif" + botInlineResult,1);
    }
 else {
      return;
    }
    AndroidUtilities.cancelRunOnUIThread(showSheetRunnable);
    AndroidUtilities.runOnUIThread(showSheetRunnable,2000);
  }
  currentContentType=contentType;
  currentDocument=document;
  inlineResult=botInlineResult;
  containerView.invalidate();
  if (!isVisible) {
    AndroidUtilities.lockOrientation(parentActivity);
    try {
      if (windowView.getParent() != null) {
        WindowManager wm=(WindowManager)parentActivity.getSystemService(Context.WINDOW_SERVICE);
        wm.removeView(windowView);
      }
    }
 catch (    Exception e) {
      FileLog.e(e);
    }
    WindowManager wm=(WindowManager)parentActivity.getSystemService(Context.WINDOW_SERVICE);
    wm.addView(windowView,windowLayoutParams);
    isVisible=true;
    showProgress=0.0f;
    lastTouchY=-10000;
    currentMoveYProgress=0.0f;
    finalMoveY=0;
    currentMoveY=0;
    moveY=0;
    lastUpdateTime=System.currentTimeMillis();
  }
}
