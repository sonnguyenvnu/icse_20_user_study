private int createDocumentLayout(int maxWidth,MessageObject messageObject){
  if (messageObject.type == 0) {
    documentAttach=messageObject.messageOwner.media.webpage.document;
  }
 else {
    documentAttach=messageObject.messageOwner.media.document;
  }
  if (documentAttach == null) {
    return 0;
  }
  if (MessageObject.isVoiceDocument(documentAttach)) {
    documentAttachType=DOCUMENT_ATTACH_TYPE_AUDIO;
    int duration=0;
    for (int a=0; a < documentAttach.attributes.size(); a++) {
      TLRPC.DocumentAttribute attribute=documentAttach.attributes.get(a);
      if (attribute instanceof TLRPC.TL_documentAttributeAudio) {
        duration=attribute.duration;
        break;
      }
    }
    widthBeforeNewTimeLine=maxWidth - AndroidUtilities.dp(76 + 18) - (int)Math.ceil(Theme.chat_audioTimePaint.measureText("00:00"));
    availableTimeWidth=maxWidth - AndroidUtilities.dp(18);
    measureTime(messageObject);
    int minSize=AndroidUtilities.dp(40 + 14 + 20 + 90 + 10) + timeWidth;
    if (!hasLinkPreview) {
      backgroundWidth=Math.min(maxWidth,minSize + duration * AndroidUtilities.dp(10));
    }
    seekBarWaveform.setMessageObject(messageObject);
    return 0;
  }
 else   if (MessageObject.isMusicDocument(documentAttach)) {
    documentAttachType=DOCUMENT_ATTACH_TYPE_MUSIC;
    maxWidth=maxWidth - AndroidUtilities.dp(86);
    if (maxWidth < 0) {
      maxWidth=AndroidUtilities.dp(100);
    }
    CharSequence stringFinal=TextUtils.ellipsize(messageObject.getMusicTitle().replace('\n',' '),Theme.chat_audioTitlePaint,maxWidth - AndroidUtilities.dp(12),TextUtils.TruncateAt.END);
    songLayout=new StaticLayout(stringFinal,Theme.chat_audioTitlePaint,maxWidth,Layout.Alignment.ALIGN_NORMAL,1.0f,0.0f,false);
    if (songLayout.getLineCount() > 0) {
      songX=-(int)Math.ceil(songLayout.getLineLeft(0));
    }
    stringFinal=TextUtils.ellipsize(messageObject.getMusicAuthor().replace('\n',' '),Theme.chat_audioPerformerPaint,maxWidth,TextUtils.TruncateAt.END);
    performerLayout=new StaticLayout(stringFinal,Theme.chat_audioPerformerPaint,maxWidth,Layout.Alignment.ALIGN_NORMAL,1.0f,0.0f,false);
    if (performerLayout.getLineCount() > 0) {
      performerX=-(int)Math.ceil(performerLayout.getLineLeft(0));
    }
    int duration=0;
    for (int a=0; a < documentAttach.attributes.size(); a++) {
      TLRPC.DocumentAttribute attribute=documentAttach.attributes.get(a);
      if (attribute instanceof TLRPC.TL_documentAttributeAudio) {
        duration=attribute.duration;
        break;
      }
    }
    int durationWidth=(int)Math.ceil(Theme.chat_audioTimePaint.measureText(String.format("%d:%02d / %d:%02d",duration / 60,duration % 60,duration / 60,duration % 60)));
    widthBeforeNewTimeLine=backgroundWidth - AndroidUtilities.dp(10 + 76) - durationWidth;
    availableTimeWidth=backgroundWidth - AndroidUtilities.dp(28);
    return durationWidth;
  }
 else   if (MessageObject.isVideoDocument(documentAttach)) {
    documentAttachType=DOCUMENT_ATTACH_TYPE_VIDEO;
    if (!messageObject.needDrawBluredPreview()) {
      updatePlayingMessageProgress();
      String str=String.format("%s",AndroidUtilities.formatFileSize(documentAttach.size));
      docTitleWidth=(int)Math.ceil(Theme.chat_infoPaint.measureText(str));
      docTitleLayout=new StaticLayout(str,Theme.chat_infoPaint,docTitleWidth,Layout.Alignment.ALIGN_NORMAL,1.0f,0.0f,false);
    }
    return 0;
  }
 else   if (MessageObject.isGifDocument(documentAttach)) {
    documentAttachType=DOCUMENT_ATTACH_TYPE_GIF;
    if (!messageObject.needDrawBluredPreview()) {
      String str=LocaleController.getString("AttachGif",R.string.AttachGif);
      infoWidth=(int)Math.ceil(Theme.chat_infoPaint.measureText(str));
      infoLayout=new StaticLayout(str,Theme.chat_infoPaint,infoWidth,Layout.Alignment.ALIGN_NORMAL,1.0f,0.0f,false);
      str=String.format("%s",AndroidUtilities.formatFileSize(documentAttach.size));
      docTitleWidth=(int)Math.ceil(Theme.chat_infoPaint.measureText(str));
      docTitleLayout=new StaticLayout(str,Theme.chat_infoPaint,docTitleWidth,Layout.Alignment.ALIGN_NORMAL,1.0f,0.0f,false);
    }
    return 0;
  }
 else {
    drawPhotoImage=documentAttach.mime_type != null && documentAttach.mime_type.toLowerCase().startsWith("image/") || MessageObject.isDocumentHasThumb(documentAttach);
    if (!drawPhotoImage) {
      maxWidth+=AndroidUtilities.dp(30);
    }
    documentAttachType=DOCUMENT_ATTACH_TYPE_DOCUMENT;
    String name=FileLoader.getDocumentFileName(documentAttach);
    if (name == null || name.length() == 0) {
      name=LocaleController.getString("AttachDocument",R.string.AttachDocument);
    }
    docTitleLayout=StaticLayoutEx.createStaticLayout(name,Theme.chat_docNamePaint,maxWidth,Layout.Alignment.ALIGN_NORMAL,1.0f,0.0f,false,TextUtils.TruncateAt.MIDDLE,maxWidth,2,false);
    docTitleOffsetX=Integer.MIN_VALUE;
    int width;
    if (docTitleLayout != null && docTitleLayout.getLineCount() > 0) {
      int maxLineWidth=0;
      for (int a=0; a < docTitleLayout.getLineCount(); a++) {
        maxLineWidth=Math.max(maxLineWidth,(int)Math.ceil(docTitleLayout.getLineWidth(a)));
        docTitleOffsetX=Math.max(docTitleOffsetX,(int)Math.ceil(-docTitleLayout.getLineLeft(a)));
      }
      width=Math.min(maxWidth,maxLineWidth);
    }
 else {
      width=maxWidth;
      docTitleOffsetX=0;
    }
    String str=AndroidUtilities.formatFileSize(documentAttach.size) + " " + FileLoader.getDocumentExtension(documentAttach);
    infoWidth=Math.min(maxWidth - AndroidUtilities.dp(30),(int)Math.ceil(Theme.chat_infoPaint.measureText(str)));
    CharSequence str2=TextUtils.ellipsize(str,Theme.chat_infoPaint,infoWidth,TextUtils.TruncateAt.END);
    try {
      if (infoWidth < 0) {
        infoWidth=AndroidUtilities.dp(10);
      }
      infoLayout=new StaticLayout(str2,Theme.chat_infoPaint,infoWidth,Layout.Alignment.ALIGN_NORMAL,1.0f,0.0f,false);
    }
 catch (    Exception e) {
      FileLog.e(e);
    }
    if (drawPhotoImage) {
      currentPhotoObject=FileLoader.getClosestPhotoSizeWithSize(messageObject.photoThumbs,320);
      currentPhotoObjectThumb=FileLoader.getClosestPhotoSizeWithSize(messageObject.photoThumbs,40);
      if ((DownloadController.getInstance(currentAccount).getAutodownloadMask() & DownloadController.AUTODOWNLOAD_TYPE_PHOTO) == 0) {
        currentPhotoObject=null;
      }
      if (currentPhotoObject == null || currentPhotoObject == currentPhotoObjectThumb) {
        currentPhotoObject=null;
        photoImage.setNeedsQualityThumb(true);
        photoImage.setShouldGenerateQualityThumb(true);
      }
      currentPhotoFilter="86_86_b";
      photoImage.setImage(ImageLocation.getForObject(currentPhotoObject,messageObject.photoThumbsObject),"86_86",ImageLocation.getForObject(currentPhotoObjectThumb,messageObject.photoThumbsObject),currentPhotoFilter,0,null,messageObject,1);
    }
    return width;
  }
}
