private void setMessageContent(MessageObject messageObject,MessageObject.GroupedMessages groupedMessages,boolean bottomNear,boolean topNear){
  if (messageObject.checkLayout() || currentPosition != null && lastHeight != AndroidUtilities.displaySize.y) {
    currentMessageObject=null;
  }
  lastHeight=AndroidUtilities.displaySize.y;
  boolean messageIdChanged=currentMessageObject == null || currentMessageObject.getId() != messageObject.getId();
  boolean messageChanged=currentMessageObject != messageObject || messageObject.forceUpdate;
  boolean dataChanged=currentMessageObject != null && currentMessageObject.getId() == messageObject.getId() && lastSendState == MessageObject.MESSAGE_SEND_STATE_EDITING && messageObject.isSent() || currentMessageObject == messageObject && (isUserDataChanged() || photoNotSet);
  boolean groupChanged=groupedMessages != currentMessagesGroup;
  boolean pollChanged=false;
  if (!messageChanged && messageObject.type == MessageObject.TYPE_POLL) {
    ArrayList<TLRPC.TL_pollAnswerVoters> newResults=null;
    TLRPC.TL_poll newPoll=null;
    int newVoters=0;
    if (messageObject.messageOwner.media instanceof TLRPC.TL_messageMediaPoll) {
      TLRPC.TL_messageMediaPoll mediaPoll=(TLRPC.TL_messageMediaPoll)messageObject.messageOwner.media;
      newResults=mediaPoll.results.results;
      newPoll=mediaPoll.poll;
      newVoters=mediaPoll.results.total_voters;
    }
    if (newResults != null && lastPollResults != null && newVoters != lastPollResultsVoters) {
      pollChanged=true;
    }
    if (!pollChanged && newResults != lastPollResults) {
      pollChanged=true;
    }
    if (!pollChanged && lastPoll != newPoll && lastPoll.closed != newPoll.closed) {
      pollChanged=true;
    }
    if (pollChanged && attachedToWindow) {
      pollAnimationProgressTime=0.0f;
      if (pollVoted && !messageObject.isVoted()) {
        pollUnvoteInProgress=true;
      }
    }
  }
  if (!groupChanged && groupedMessages != null) {
    MessageObject.GroupedMessagePosition newPosition;
    if (groupedMessages.messages.size() > 1) {
      newPosition=currentMessagesGroup.positions.get(currentMessageObject);
    }
 else {
      newPosition=null;
    }
    groupChanged=newPosition != currentPosition;
  }
  if (messageChanged || dataChanged || groupChanged || pollChanged || isPhotoDataChanged(messageObject) || pinnedBottom != bottomNear || pinnedTop != topNear) {
    pinnedBottom=bottomNear;
    pinnedTop=topNear;
    currentMessageObject=messageObject;
    currentMessagesGroup=groupedMessages;
    lastTime=-2;
    isHighlightedAnimated=false;
    widthBeforeNewTimeLine=-1;
    if (currentMessagesGroup != null && currentMessagesGroup.posArray.size() > 1) {
      currentPosition=currentMessagesGroup.positions.get(currentMessageObject);
      if (currentPosition == null) {
        currentMessagesGroup=null;
      }
    }
 else {
      currentMessagesGroup=null;
      currentPosition=null;
    }
    drawPinnedTop=pinnedTop && (currentPosition == null || (currentPosition.flags & MessageObject.POSITION_FLAG_TOP) != 0);
    drawPinnedBottom=pinnedBottom && (currentPosition == null || (currentPosition.flags & MessageObject.POSITION_FLAG_BOTTOM) != 0);
    photoImage.setCrossfadeWithOldImage(false);
    lastSendState=messageObject.messageOwner.send_state;
    lastDeleteDate=messageObject.messageOwner.destroyTime;
    lastViewsCount=messageObject.messageOwner.views;
    isPressed=false;
    gamePreviewPressed=false;
    sharePressed=false;
    isCheckPressed=true;
    hasNewLineForTime=false;
    isAvatarVisible=isChat && !messageObject.isOutOwner() && messageObject.needDrawAvatar() && (currentPosition == null || currentPosition.edge);
    wasLayout=false;
    drwaShareGoIcon=false;
    groupPhotoInvisible=false;
    animatingDrawVideoImageButton=0;
    drawVideoSize=false;
    canStreamVideo=false;
    animatingNoSound=0;
    drawShareButton=checkNeedDrawShareButton(messageObject);
    replyNameLayout=null;
    adminLayout=null;
    checkOnlyButtonPressed=false;
    replyTextLayout=null;
    hasEmbed=false;
    autoPlayingMedia=false;
    replyNameWidth=0;
    replyTextWidth=0;
    viaWidth=0;
    viaNameWidth=0;
    addedCaptionHeight=0;
    currentReplyPhoto=null;
    currentUser=null;
    currentChat=null;
    currentViaBotUser=null;
    instantViewLayout=null;
    drawNameLayout=false;
    if (scheduledInvalidate) {
      AndroidUtilities.cancelRunOnUIThread(invalidateRunnable);
      scheduledInvalidate=false;
    }
    resetPressedLink(-1);
    messageObject.forceUpdate=false;
    drawPhotoImage=false;
    drawPhotoCheckBox=false;
    hasLinkPreview=false;
    hasOldCaptionPreview=false;
    hasGamePreview=false;
    hasInvoicePreview=false;
    instantPressed=instantButtonPressed=false;
    if (!pollChanged && Build.VERSION.SDK_INT >= 21 && selectorDrawable != null) {
      selectorDrawable.setVisible(false,false);
      selectorDrawable.setState(StateSet.NOTHING);
    }
    linkPreviewPressed=false;
    buttonPressed=0;
    miniButtonPressed=0;
    pressedBotButton=-1;
    pressedVoteButton=-1;
    linkPreviewHeight=0;
    mediaOffsetY=0;
    documentAttachType=DOCUMENT_ATTACH_TYPE_NONE;
    documentAttach=null;
    descriptionLayout=null;
    titleLayout=null;
    videoInfoLayout=null;
    photosCountLayout=null;
    siteNameLayout=null;
    authorLayout=null;
    captionLayout=null;
    captionOffsetX=0;
    currentCaption=null;
    docTitleLayout=null;
    drawImageButton=false;
    drawVideoImageButton=false;
    currentPhotoObject=null;
    photoParentObject=null;
    currentPhotoObjectThumb=null;
    currentPhotoFilter=null;
    infoLayout=null;
    cancelLoading=false;
    buttonState=-1;
    miniButtonState=-1;
    hasMiniProgress=0;
    if (addedForTest && currentUrl != null && currentWebFile != null) {
      ImageLoader.getInstance().removeTestWebFile(currentUrl);
    }
    addedForTest=false;
    currentUrl=null;
    currentWebFile=null;
    photoNotSet=false;
    drawBackground=true;
    drawName=false;
    useSeekBarWaweform=false;
    drawInstantView=false;
    drawInstantViewType=0;
    drawForwardedName=false;
    photoImage.setSideClip(0);
    imageBackgroundColor=0;
    imageBackgroundSideColor=0;
    mediaBackground=false;
    photoImage.setAlpha(1.0f);
    if (messageChanged || dataChanged) {
      pollButtons.clear();
    }
    int captionNewLine=0;
    availableTimeWidth=0;
    photoImage.setForceLoading(false);
    photoImage.setNeedsQualityThumb(false);
    photoImage.setShouldGenerateQualityThumb(false);
    photoImage.setAllowDecodeSingleFrame(false);
    photoImage.setRoundRadius(AndroidUtilities.dp(4));
    photoImage.setColorFilter(null);
    if (messageChanged) {
      firstVisibleBlockNum=0;
      lastVisibleBlockNum=0;
      needNewVisiblePart=true;
    }
    if (messageObject.type == 0) {
      drawForwardedName=true;
      int maxWidth;
      if (AndroidUtilities.isTablet()) {
        if (isChat && !messageObject.isOutOwner() && messageObject.needDrawAvatar()) {
          maxWidth=AndroidUtilities.getMinTabletSide() - AndroidUtilities.dp(122);
          drawName=true;
        }
 else {
          drawName=messageObject.messageOwner.to_id.channel_id != 0 && !messageObject.isOutOwner();
          maxWidth=AndroidUtilities.getMinTabletSide() - AndroidUtilities.dp(80);
        }
      }
 else {
        if (isChat && !messageObject.isOutOwner() && messageObject.needDrawAvatar()) {
          maxWidth=Math.min(AndroidUtilities.displaySize.x,AndroidUtilities.displaySize.y) - AndroidUtilities.dp(122);
          drawName=true;
        }
 else {
          maxWidth=Math.min(AndroidUtilities.displaySize.x,AndroidUtilities.displaySize.y) - AndroidUtilities.dp(80);
          drawName=messageObject.messageOwner.to_id.channel_id != 0 && !messageObject.isOutOwner();
        }
      }
      availableTimeWidth=maxWidth;
      if (messageObject.isRoundVideo()) {
        availableTimeWidth-=Math.ceil(Theme.chat_audioTimePaint.measureText("00:00")) + (messageObject.isOutOwner() ? 0 : AndroidUtilities.dp(64));
      }
      measureTime(messageObject);
      int timeMore=timeWidth + AndroidUtilities.dp(6);
      if (messageObject.isOutOwner()) {
        timeMore+=AndroidUtilities.dp(20.5f);
      }
      hasGamePreview=messageObject.messageOwner.media instanceof TLRPC.TL_messageMediaGame && messageObject.messageOwner.media.game instanceof TLRPC.TL_game;
      hasInvoicePreview=messageObject.messageOwner.media instanceof TLRPC.TL_messageMediaInvoice;
      hasLinkPreview=messageObject.messageOwner.media instanceof TLRPC.TL_messageMediaWebPage && messageObject.messageOwner.media.webpage instanceof TLRPC.TL_webPage;
      drawInstantView=hasLinkPreview && messageObject.messageOwner.media.webpage.cached_page != null;
      hasEmbed=hasLinkPreview && !TextUtils.isEmpty(messageObject.messageOwner.media.webpage.embed_url) && !messageObject.isGif();
      boolean slideshow=false;
      String siteName=hasLinkPreview ? messageObject.messageOwner.media.webpage.site_name : null;
      String webpageType=hasLinkPreview ? messageObject.messageOwner.media.webpage.type : null;
      if (!drawInstantView) {
        if ("telegram_channel".equals(webpageType)) {
          drawInstantView=true;
          drawInstantViewType=1;
        }
 else         if ("telegram_megagroup".equals(webpageType)) {
          drawInstantView=true;
          drawInstantViewType=2;
        }
 else         if ("telegram_message".equals(webpageType)) {
          drawInstantView=true;
          drawInstantViewType=3;
        }
 else         if ("telegram_background".equals(webpageType)) {
          drawInstantView=true;
          drawInstantViewType=6;
          try {
            Uri url=Uri.parse(messageObject.messageOwner.media.webpage.url);
            int intensity=Utilities.parseInt(url.getQueryParameter("intensity"));
            String bgColor=url.getQueryParameter("bg_color");
            if (TextUtils.isEmpty(bgColor)) {
              TLRPC.Document document=messageObject.getDocument();
              if (document != null && "image/png".equals(document.mime_type)) {
                bgColor="ffffff";
              }
              if (intensity == 0) {
                intensity=50;
              }
            }
            if (bgColor != null) {
              imageBackgroundColor=Integer.parseInt(bgColor,16) | 0xff000000;
              imageBackgroundSideColor=AndroidUtilities.getPatternSideColor(imageBackgroundColor);
              photoImage.setColorFilter(new PorterDuffColorFilter(AndroidUtilities.getPatternColor(imageBackgroundColor),PorterDuff.Mode.SRC_IN));
              photoImage.setAlpha(intensity / 100.0f);
            }
 else {
              String color=url.getLastPathSegment();
              if (color != null && color.length() == 6) {
                imageBackgroundColor=Integer.parseInt(color,16) | 0xff000000;
                currentPhotoObject=new TLRPC.TL_photoSizeEmpty();
                currentPhotoObject.type="s";
                currentPhotoObject.w=AndroidUtilities.dp(180);
                currentPhotoObject.h=AndroidUtilities.dp(150);
                currentPhotoObject.location=new TLRPC.TL_fileLocationUnavailable();
              }
            }
          }
 catch (          Exception ignore) {
          }
        }
      }
 else       if (siteName != null) {
        siteName=siteName.toLowerCase();
        if ((siteName.equals("instagram") || siteName.equals("twitter") || "telegram_album".equals(webpageType)) && messageObject.messageOwner.media.webpage.cached_page instanceof TLRPC.TL_page && (messageObject.messageOwner.media.webpage.photo instanceof TLRPC.TL_photo || MessageObject.isVideoDocument(messageObject.messageOwner.media.webpage.document))) {
          drawInstantView=false;
          slideshow=true;
          ArrayList<TLRPC.PageBlock> blocks=messageObject.messageOwner.media.webpage.cached_page.blocks;
          int count=1;
          for (int a=0; a < blocks.size(); a++) {
            TLRPC.PageBlock block=blocks.get(a);
            if (block instanceof TLRPC.TL_pageBlockSlideshow) {
              TLRPC.TL_pageBlockSlideshow b=(TLRPC.TL_pageBlockSlideshow)block;
              count=b.items.size();
            }
 else             if (block instanceof TLRPC.TL_pageBlockCollage) {
              TLRPC.TL_pageBlockCollage b=(TLRPC.TL_pageBlockCollage)block;
              count=b.items.size();
            }
          }
          String str=LocaleController.formatString("Of",R.string.Of,1,count);
          photosCountWidth=(int)Math.ceil(Theme.chat_durationPaint.measureText(str));
          photosCountLayout=new StaticLayout(str,Theme.chat_durationPaint,photosCountWidth,Layout.Alignment.ALIGN_NORMAL,1.0f,0.0f,false);
        }
      }
      backgroundWidth=maxWidth;
      if (hasLinkPreview || hasGamePreview || hasInvoicePreview || maxWidth - messageObject.lastLineWidth < timeMore) {
        backgroundWidth=Math.max(backgroundWidth,messageObject.lastLineWidth) + AndroidUtilities.dp(31);
        backgroundWidth=Math.max(backgroundWidth,timeWidth + AndroidUtilities.dp(31));
      }
 else {
        int diff=backgroundWidth - messageObject.lastLineWidth;
        if (diff >= 0 && diff <= timeMore) {
          backgroundWidth=backgroundWidth + timeMore - diff + AndroidUtilities.dp(31);
        }
 else {
          backgroundWidth=Math.max(backgroundWidth,messageObject.lastLineWidth + timeMore) + AndroidUtilities.dp(31);
        }
      }
      availableTimeWidth=backgroundWidth - AndroidUtilities.dp(31);
      if (messageObject.isRoundVideo()) {
        availableTimeWidth-=Math.ceil(Theme.chat_audioTimePaint.measureText("00:00")) + (messageObject.isOutOwner() ? 0 : AndroidUtilities.dp(64));
      }
      setMessageObjectInternal(messageObject);
      backgroundWidth=messageObject.textWidth + ((hasGamePreview || hasInvoicePreview) ? AndroidUtilities.dp(10) : 0);
      totalHeight=messageObject.textHeight + AndroidUtilities.dp(19.5f) + namesOffset;
      if (drawPinnedTop) {
        namesOffset-=AndroidUtilities.dp(1);
      }
      int maxChildWidth=Math.max(backgroundWidth,nameWidth);
      maxChildWidth=Math.max(maxChildWidth,forwardedNameWidth);
      maxChildWidth=Math.max(maxChildWidth,replyNameWidth);
      maxChildWidth=Math.max(maxChildWidth,replyTextWidth);
      int maxWebWidth=0;
      if (hasLinkPreview || hasGamePreview || hasInvoicePreview) {
        int linkPreviewMaxWidth;
        if (AndroidUtilities.isTablet()) {
          if (isChat && messageObject.needDrawAvatar() && !currentMessageObject.isOutOwner()) {
            linkPreviewMaxWidth=AndroidUtilities.getMinTabletSide() - AndroidUtilities.dp(132);
          }
 else {
            linkPreviewMaxWidth=AndroidUtilities.getMinTabletSide() - AndroidUtilities.dp(80);
          }
        }
 else {
          if (isChat && messageObject.needDrawAvatar() && !currentMessageObject.isOutOwner()) {
            linkPreviewMaxWidth=AndroidUtilities.displaySize.x - AndroidUtilities.dp(132);
          }
 else {
            linkPreviewMaxWidth=AndroidUtilities.displaySize.x - AndroidUtilities.dp(80);
          }
        }
        if (drawShareButton) {
          linkPreviewMaxWidth-=AndroidUtilities.dp(20);
        }
        String site_name;
        String title;
        String author;
        String description;
        TLRPC.Photo photo;
        TLRPC.Document document;
        WebFile webDocument;
        int duration;
        boolean smallImage;
        String type;
        if (hasLinkPreview) {
          TLRPC.TL_webPage webPage=(TLRPC.TL_webPage)messageObject.messageOwner.media.webpage;
          site_name=webPage.site_name;
          title=drawInstantViewType != 6 ? webPage.title : null;
          author=drawInstantViewType != 6 ? webPage.author : null;
          description=drawInstantViewType != 6 ? webPage.description : null;
          photo=webPage.photo;
          webDocument=null;
          document=webPage.document;
          type=webPage.type;
          duration=webPage.duration;
          if (site_name != null && photo != null && site_name.toLowerCase().equals("instagram")) {
            linkPreviewMaxWidth=Math.max(AndroidUtilities.displaySize.y / 3,currentMessageObject.textWidth);
          }
          boolean isSmallImageType="app".equals(type) || "profile".equals(type) || "article".equals(type);
          smallImage=!slideshow && !drawInstantView && document == null && isSmallImageType;
          isSmallImage=!slideshow && !drawInstantView && document == null && description != null && type != null && isSmallImageType && currentMessageObject.photoThumbs != null;
        }
 else         if (hasInvoicePreview) {
          TLRPC.TL_messageMediaInvoice invoice=(TLRPC.TL_messageMediaInvoice)messageObject.messageOwner.media;
          site_name=messageObject.messageOwner.media.title;
          title=null;
          description=null;
          photo=null;
          author=null;
          document=null;
          if (invoice.photo instanceof TLRPC.TL_webDocument) {
            webDocument=WebFile.createWithWebDocument(invoice.photo);
          }
 else {
            webDocument=null;
          }
          duration=0;
          type="invoice";
          isSmallImage=false;
          smallImage=false;
        }
 else {
          TLRPC.TL_game game=messageObject.messageOwner.media.game;
          site_name=game.title;
          title=null;
          webDocument=null;
          description=TextUtils.isEmpty(messageObject.messageText) ? game.description : null;
          photo=game.photo;
          author=null;
          document=game.document;
          duration=0;
          type="game";
          isSmallImage=false;
          smallImage=false;
        }
        if (drawInstantViewType == 6) {
          site_name=LocaleController.getString("ChatBackground",R.string.ChatBackground);
        }
        int additinalWidth=hasInvoicePreview ? 0 : AndroidUtilities.dp(10);
        int restLinesCount=3;
        int additionalHeight=0;
        linkPreviewMaxWidth-=additinalWidth;
        if (currentMessageObject.photoThumbs == null && photo != null) {
          currentMessageObject.generateThumbs(true);
        }
        if (site_name != null) {
          try {
            int width=(int)Math.ceil(Theme.chat_replyNamePaint.measureText(site_name) + 1);
            siteNameLayout=new StaticLayout(site_name,Theme.chat_replyNamePaint,Math.min(width,linkPreviewMaxWidth),Layout.Alignment.ALIGN_NORMAL,1.0f,0.0f,false);
            siteNameRtl=siteNameLayout.getLineLeft(0) != 0;
            int height=siteNameLayout.getLineBottom(siteNameLayout.getLineCount() - 1);
            linkPreviewHeight+=height;
            totalHeight+=height;
            additionalHeight+=height;
            siteNameWidth=width=siteNameLayout.getWidth();
            maxChildWidth=Math.max(maxChildWidth,width + additinalWidth);
            maxWebWidth=Math.max(maxWebWidth,width + additinalWidth);
          }
 catch (          Exception e) {
            FileLog.e(e);
          }
        }
        boolean titleIsRTL=false;
        if (title != null) {
          try {
            titleX=Integer.MAX_VALUE;
            if (linkPreviewHeight != 0) {
              linkPreviewHeight+=AndroidUtilities.dp(2);
              totalHeight+=AndroidUtilities.dp(2);
            }
            int restLines=0;
            if (!isSmallImage || description == null) {
              titleLayout=StaticLayoutEx.createStaticLayout(title,Theme.chat_replyNamePaint,linkPreviewMaxWidth,Layout.Alignment.ALIGN_NORMAL,1.0f,AndroidUtilities.dp(1),false,TextUtils.TruncateAt.END,linkPreviewMaxWidth,4);
            }
 else {
              restLines=restLinesCount;
              titleLayout=generateStaticLayout(title,Theme.chat_replyNamePaint,linkPreviewMaxWidth,linkPreviewMaxWidth - AndroidUtilities.dp(48 + 4),restLinesCount,4);
              restLinesCount-=titleLayout.getLineCount();
            }
            int height=titleLayout.getLineBottom(titleLayout.getLineCount() - 1);
            linkPreviewHeight+=height;
            totalHeight+=height;
            boolean checkForRtl=true;
            for (int a=0; a < titleLayout.getLineCount(); a++) {
              int lineLeft=(int)titleLayout.getLineLeft(a);
              if (lineLeft != 0) {
                titleIsRTL=true;
              }
              if (titleX == Integer.MAX_VALUE) {
                titleX=-lineLeft;
              }
 else {
                titleX=Math.max(titleX,-lineLeft);
              }
              int width;
              if (lineLeft != 0) {
                width=titleLayout.getWidth() - lineLeft;
              }
 else {
                width=(int)Math.ceil(titleLayout.getLineWidth(a));
              }
              if (a < restLines || lineLeft != 0 && isSmallImage) {
                width+=AndroidUtilities.dp(48 + 4);
              }
              maxChildWidth=Math.max(maxChildWidth,width + additinalWidth);
              maxWebWidth=Math.max(maxWebWidth,width + additinalWidth);
            }
          }
 catch (          Exception e) {
            FileLog.e(e);
          }
          if (titleIsRTL && isSmallImage) {
            linkPreviewMaxWidth-=AndroidUtilities.dp(48);
          }
        }
        boolean authorIsRTL=false;
        if (author != null && title == null) {
          try {
            if (linkPreviewHeight != 0) {
              linkPreviewHeight+=AndroidUtilities.dp(2);
              totalHeight+=AndroidUtilities.dp(2);
            }
            if (restLinesCount == 3 && (!isSmallImage || description == null)) {
              authorLayout=new StaticLayout(author,Theme.chat_replyNamePaint,linkPreviewMaxWidth,Layout.Alignment.ALIGN_NORMAL,1.0f,0.0f,false);
            }
 else {
              authorLayout=generateStaticLayout(author,Theme.chat_replyNamePaint,linkPreviewMaxWidth,linkPreviewMaxWidth - AndroidUtilities.dp(48 + 4),restLinesCount,1);
              restLinesCount-=authorLayout.getLineCount();
            }
            int height=authorLayout.getLineBottom(authorLayout.getLineCount() - 1);
            linkPreviewHeight+=height;
            totalHeight+=height;
            int lineLeft=(int)authorLayout.getLineLeft(0);
            authorX=-lineLeft;
            int width;
            if (lineLeft != 0) {
              width=authorLayout.getWidth() - lineLeft;
              authorIsRTL=true;
            }
 else {
              width=(int)Math.ceil(authorLayout.getLineWidth(0));
            }
            maxChildWidth=Math.max(maxChildWidth,width + additinalWidth);
            maxWebWidth=Math.max(maxWebWidth,width + additinalWidth);
          }
 catch (          Exception e) {
            FileLog.e(e);
          }
        }
        if (description != null) {
          try {
            descriptionX=0;
            currentMessageObject.generateLinkDescription();
            if (linkPreviewHeight != 0) {
              linkPreviewHeight+=AndroidUtilities.dp(2);
              totalHeight+=AndroidUtilities.dp(2);
            }
            int restLines=0;
            boolean allowAllLines=site_name != null && site_name.toLowerCase().equals("twitter");
            if (restLinesCount == 3 && !isSmallImage) {
              descriptionLayout=StaticLayoutEx.createStaticLayout(messageObject.linkDescription,Theme.chat_replyTextPaint,linkPreviewMaxWidth,Layout.Alignment.ALIGN_NORMAL,1.0f,AndroidUtilities.dp(1),false,TextUtils.TruncateAt.END,linkPreviewMaxWidth,allowAllLines ? 100 : 6);
            }
 else {
              restLines=restLinesCount;
              descriptionLayout=generateStaticLayout(messageObject.linkDescription,Theme.chat_replyTextPaint,linkPreviewMaxWidth,linkPreviewMaxWidth - AndroidUtilities.dp(48 + 4),restLinesCount,allowAllLines ? 100 : 6);
            }
            int height=descriptionLayout.getLineBottom(descriptionLayout.getLineCount() - 1);
            linkPreviewHeight+=height;
            totalHeight+=height;
            boolean hasRTL=false;
            for (int a=0; a < descriptionLayout.getLineCount(); a++) {
              int lineLeft=(int)Math.ceil(descriptionLayout.getLineLeft(a));
              if (lineLeft != 0) {
                hasRTL=true;
                if (descriptionX == 0) {
                  descriptionX=-lineLeft;
                }
 else {
                  descriptionX=Math.max(descriptionX,-lineLeft);
                }
              }
            }
            int textWidth=descriptionLayout.getWidth();
            for (int a=0; a < descriptionLayout.getLineCount(); a++) {
              int lineLeft=(int)Math.ceil(descriptionLayout.getLineLeft(a));
              if (lineLeft == 0 && descriptionX != 0) {
                descriptionX=0;
              }
              int width;
              if (lineLeft != 0) {
                width=textWidth - lineLeft;
              }
 else {
                if (hasRTL) {
                  width=textWidth;
                }
 else {
                  width=Math.min((int)Math.ceil(descriptionLayout.getLineWidth(a)),textWidth);
                }
              }
              if (a < restLines || restLines != 0 && lineLeft != 0 && isSmallImage) {
                width+=AndroidUtilities.dp(48 + 4);
              }
              if (maxWebWidth < width + additinalWidth) {
                if (titleIsRTL) {
                  titleX+=(width + additinalWidth - maxWebWidth);
                }
                if (authorIsRTL) {
                  authorX+=(width + additinalWidth - maxWebWidth);
                }
                maxWebWidth=width + additinalWidth;
              }
              maxChildWidth=Math.max(maxChildWidth,width + additinalWidth);
            }
          }
 catch (          Exception e) {
            FileLog.e(e);
          }
        }
        if (smallImage && (descriptionLayout == null || descriptionLayout != null && descriptionLayout.getLineCount() == 1)) {
          smallImage=false;
          isSmallImage=false;
        }
        int maxPhotoWidth=smallImage ? AndroidUtilities.dp(48) : linkPreviewMaxWidth;
        if (document != null) {
          if (MessageObject.isRoundVideoDocument(document)) {
            currentPhotoObject=FileLoader.getClosestPhotoSizeWithSize(document.thumbs,90);
            photoParentObject=document;
            documentAttach=document;
            documentAttachType=DOCUMENT_ATTACH_TYPE_ROUND;
          }
 else           if (MessageObject.isGifDocument(document)) {
            if (!messageObject.isGame() && !SharedConfig.autoplayGifs) {
              messageObject.gifState=1;
            }
            photoImage.setAllowStartAnimation(messageObject.gifState != 1);
            currentPhotoObject=FileLoader.getClosestPhotoSizeWithSize(document.thumbs,90);
            photoParentObject=document;
            if (currentPhotoObject != null && (currentPhotoObject.w == 0 || currentPhotoObject.h == 0)) {
              for (int a=0; a < document.attributes.size(); a++) {
                TLRPC.DocumentAttribute attribute=document.attributes.get(a);
                if (attribute instanceof TLRPC.TL_documentAttributeImageSize || attribute instanceof TLRPC.TL_documentAttributeVideo) {
                  currentPhotoObject.w=attribute.w;
                  currentPhotoObject.h=attribute.h;
                  break;
                }
              }
              if (currentPhotoObject.w == 0 || currentPhotoObject.h == 0) {
                currentPhotoObject.w=currentPhotoObject.h=AndroidUtilities.dp(150);
              }
            }
            documentAttach=document;
            documentAttachType=DOCUMENT_ATTACH_TYPE_GIF;
          }
 else           if (MessageObject.isVideoDocument(document)) {
            if (photo != null) {
              currentPhotoObject=FileLoader.getClosestPhotoSizeWithSize(photo.sizes,AndroidUtilities.getPhotoSize(),true);
              currentPhotoObjectThumb=FileLoader.getClosestPhotoSizeWithSize(photo.sizes,40);
              photoParentObject=photo;
            }
            if (currentPhotoObject == null) {
              currentPhotoObject=FileLoader.getClosestPhotoSizeWithSize(document.thumbs,320);
              currentPhotoObjectThumb=FileLoader.getClosestPhotoSizeWithSize(document.thumbs,40);
              photoParentObject=document;
            }
            if (currentPhotoObject == currentPhotoObjectThumb) {
              currentPhotoObjectThumb=null;
            }
            if (currentPhotoObject == null) {
              currentPhotoObject=new TLRPC.TL_photoSize();
              currentPhotoObject.type="s";
              currentPhotoObject.location=new TLRPC.TL_fileLocationUnavailable();
            }
            if (currentPhotoObject != null && (currentPhotoObject.w == 0 || currentPhotoObject.h == 0 || currentPhotoObject instanceof TLRPC.TL_photoStrippedSize)) {
              for (int a=0; a < document.attributes.size(); a++) {
                TLRPC.DocumentAttribute attribute=document.attributes.get(a);
                if (attribute instanceof TLRPC.TL_documentAttributeVideo) {
                  if (currentPhotoObject instanceof TLRPC.TL_photoStrippedSize) {
                    float scale=Math.max(attribute.w,attribute.w) / 50.0f;
                    currentPhotoObject.w=(int)(attribute.w / scale);
                    currentPhotoObject.h=(int)(attribute.h / scale);
                  }
 else {
                    currentPhotoObject.w=attribute.w;
                    currentPhotoObject.h=attribute.h;
                  }
                  break;
                }
              }
              if (currentPhotoObject.w == 0 || currentPhotoObject.h == 0) {
                currentPhotoObject.w=currentPhotoObject.h=AndroidUtilities.dp(150);
              }
            }
            createDocumentLayout(0,messageObject);
          }
 else           if (MessageObject.isStickerDocument(document)) {
            currentPhotoObject=FileLoader.getClosestPhotoSizeWithSize(document.thumbs,90);
            photoParentObject=document;
            if (currentPhotoObject != null && (currentPhotoObject.w == 0 || currentPhotoObject.h == 0)) {
              for (int a=0; a < document.attributes.size(); a++) {
                TLRPC.DocumentAttribute attribute=document.attributes.get(a);
                if (attribute instanceof TLRPC.TL_documentAttributeImageSize) {
                  currentPhotoObject.w=attribute.w;
                  currentPhotoObject.h=attribute.h;
                  break;
                }
              }
              if (currentPhotoObject.w == 0 || currentPhotoObject.h == 0) {
                currentPhotoObject.w=currentPhotoObject.h=AndroidUtilities.dp(150);
              }
            }
            documentAttach=document;
            documentAttachType=DOCUMENT_ATTACH_TYPE_STICKER;
          }
 else           if (drawInstantViewType == 6) {
            currentPhotoObject=FileLoader.getClosestPhotoSizeWithSize(document.thumbs,320);
            photoParentObject=document;
            if (currentPhotoObject != null && (currentPhotoObject.w == 0 || currentPhotoObject.h == 0)) {
              for (int a=0; a < document.attributes.size(); a++) {
                TLRPC.DocumentAttribute attribute=document.attributes.get(a);
                if (attribute instanceof TLRPC.TL_documentAttributeImageSize) {
                  currentPhotoObject.w=attribute.w;
                  currentPhotoObject.h=attribute.h;
                  break;
                }
              }
              if (currentPhotoObject.w == 0 || currentPhotoObject.h == 0) {
                currentPhotoObject.w=currentPhotoObject.h=AndroidUtilities.dp(150);
              }
            }
            documentAttach=document;
            documentAttachType=DOCUMENT_ATTACH_TYPE_WALLPAPER;
            String str=AndroidUtilities.formatFileSize(documentAttach.size);
            durationWidth=(int)Math.ceil(Theme.chat_durationPaint.measureText(str));
            videoInfoLayout=new StaticLayout(str,Theme.chat_durationPaint,durationWidth,Layout.Alignment.ALIGN_NORMAL,1.0f,0.0f,false);
          }
 else {
            calcBackgroundWidth(maxWidth,timeMore,maxChildWidth);
            if (backgroundWidth < maxWidth + AndroidUtilities.dp(20)) {
              backgroundWidth=maxWidth + AndroidUtilities.dp(20);
            }
            if (MessageObject.isVoiceDocument(document)) {
              createDocumentLayout(backgroundWidth - AndroidUtilities.dp(10),messageObject);
              mediaOffsetY=currentMessageObject.textHeight + AndroidUtilities.dp(8) + linkPreviewHeight;
              totalHeight+=AndroidUtilities.dp(30 + 14);
              linkPreviewHeight+=AndroidUtilities.dp(44);
              maxWidth=maxWidth - AndroidUtilities.dp(86);
              if (AndroidUtilities.isTablet()) {
                maxChildWidth=Math.max(maxChildWidth,Math.min(AndroidUtilities.getMinTabletSide() - AndroidUtilities.dp(isChat && messageObject.needDrawAvatar() && !messageObject.isOutOwner() ? 52 : 0),AndroidUtilities.dp(220)) - AndroidUtilities.dp(30) + additinalWidth);
              }
 else {
                maxChildWidth=Math.max(maxChildWidth,Math.min(AndroidUtilities.displaySize.x - AndroidUtilities.dp(isChat && messageObject.needDrawAvatar() && !messageObject.isOutOwner() ? 52 : 0),AndroidUtilities.dp(220)) - AndroidUtilities.dp(30) + additinalWidth);
              }
              calcBackgroundWidth(maxWidth,timeMore,maxChildWidth);
            }
 else             if (MessageObject.isMusicDocument(document)) {
              int durationWidth=createDocumentLayout(backgroundWidth - AndroidUtilities.dp(10),messageObject);
              mediaOffsetY=currentMessageObject.textHeight + AndroidUtilities.dp(8) + linkPreviewHeight;
              totalHeight+=AndroidUtilities.dp(42 + 14);
              linkPreviewHeight+=AndroidUtilities.dp(56);
              maxWidth=maxWidth - AndroidUtilities.dp(86);
              maxChildWidth=Math.max(maxChildWidth,durationWidth + additinalWidth + AndroidUtilities.dp(86 + 8));
              if (songLayout != null && songLayout.getLineCount() > 0) {
                maxChildWidth=(int)Math.max(maxChildWidth,songLayout.getLineWidth(0) + additinalWidth + AndroidUtilities.dp(86));
              }
              if (performerLayout != null && performerLayout.getLineCount() > 0) {
                maxChildWidth=(int)Math.max(maxChildWidth,performerLayout.getLineWidth(0) + additinalWidth + AndroidUtilities.dp(86));
              }
              calcBackgroundWidth(maxWidth,timeMore,maxChildWidth);
            }
 else {
              createDocumentLayout(backgroundWidth - AndroidUtilities.dp(86 + 24 + 58),messageObject);
              drawImageButton=true;
              if (drawPhotoImage) {
                totalHeight+=AndroidUtilities.dp(86 + 14);
                linkPreviewHeight+=AndroidUtilities.dp(86);
                photoImage.setImageCoords(0,totalHeight + namesOffset,AndroidUtilities.dp(86),AndroidUtilities.dp(86));
              }
 else {
                mediaOffsetY=currentMessageObject.textHeight + AndroidUtilities.dp(8) + linkPreviewHeight;
                photoImage.setImageCoords(0,totalHeight + namesOffset - AndroidUtilities.dp(14),AndroidUtilities.dp(56),AndroidUtilities.dp(56));
                totalHeight+=AndroidUtilities.dp(50 + 14);
                linkPreviewHeight+=AndroidUtilities.dp(50);
                if (docTitleLayout != null && docTitleLayout.getLineCount() > 1) {
                  int h=(docTitleLayout.getLineCount() - 1) * AndroidUtilities.dp(16);
                  totalHeight+=h;
                  linkPreviewHeight+=h;
                }
              }
            }
          }
        }
 else         if (photo != null) {
          boolean isPhoto=type != null && type.equals("photo");
          currentPhotoObject=FileLoader.getClosestPhotoSizeWithSize(messageObject.photoThumbs,isPhoto || !smallImage ? AndroidUtilities.getPhotoSize() : maxPhotoWidth,!isPhoto);
          photoParentObject=messageObject.photoThumbsObject;
          checkOnlyButtonPressed=!isPhoto;
          currentPhotoObjectThumb=FileLoader.getClosestPhotoSizeWithSize(messageObject.photoThumbs,40);
          if (currentPhotoObjectThumb == currentPhotoObject) {
            currentPhotoObjectThumb=null;
          }
        }
 else         if (webDocument != null) {
          if (!webDocument.mime_type.startsWith("image/")) {
            webDocument=null;
          }
          drawImageButton=false;
        }
        if (documentAttachType != DOCUMENT_ATTACH_TYPE_MUSIC && documentAttachType != DOCUMENT_ATTACH_TYPE_AUDIO && documentAttachType != DOCUMENT_ATTACH_TYPE_DOCUMENT) {
          if (currentPhotoObject != null || webDocument != null) {
            drawImageButton=photo != null && !smallImage || type != null && (type.equals("photo") || type.equals("document") && documentAttachType != DOCUMENT_ATTACH_TYPE_STICKER || type.equals("gif") || documentAttachType == DOCUMENT_ATTACH_TYPE_VIDEO || documentAttachType == DOCUMENT_ATTACH_TYPE_WALLPAPER);
            if (linkPreviewHeight != 0) {
              linkPreviewHeight+=AndroidUtilities.dp(2);
              totalHeight+=AndroidUtilities.dp(2);
            }
            if (imageBackgroundSideColor != 0) {
              maxPhotoWidth=AndroidUtilities.dp(208);
            }
 else             if (currentPhotoObject instanceof TLRPC.TL_photoSizeEmpty && currentPhotoObject.w != 0) {
              maxPhotoWidth=currentPhotoObject.w;
            }
 else             if (documentAttachType == DOCUMENT_ATTACH_TYPE_STICKER || documentAttachType == DOCUMENT_ATTACH_TYPE_WALLPAPER) {
              if (AndroidUtilities.isTablet()) {
                maxPhotoWidth=(int)(AndroidUtilities.getMinTabletSide() * 0.5f);
              }
 else {
                maxPhotoWidth=(int)(AndroidUtilities.displaySize.x * 0.5f);
              }
            }
 else             if (documentAttachType == DOCUMENT_ATTACH_TYPE_ROUND) {
              maxPhotoWidth=AndroidUtilities.roundMessageSize;
              photoImage.setAllowDecodeSingleFrame(true);
            }
            maxChildWidth=Math.max(maxChildWidth,maxPhotoWidth - (hasInvoicePreview ? AndroidUtilities.dp(12) : 0) + additinalWidth);
            if (currentPhotoObject != null) {
              currentPhotoObject.size=-1;
              if (currentPhotoObjectThumb != null) {
                currentPhotoObjectThumb.size=-1;
              }
            }
 else {
              webDocument.size=-1;
            }
            if (imageBackgroundSideColor != 0) {
              imageBackgroundSideWidth=maxChildWidth - AndroidUtilities.dp(13);
            }
            int width;
            int height;
            if (smallImage || documentAttachType == DOCUMENT_ATTACH_TYPE_ROUND) {
              width=height=maxPhotoWidth;
            }
 else {
              if (hasGamePreview || hasInvoicePreview) {
                width=640;
                height=360;
                float scale=width / (float)(maxPhotoWidth - AndroidUtilities.dp(2));
                width/=scale;
                height/=scale;
              }
 else {
                width=currentPhotoObject.w;
                height=currentPhotoObject.h;
                float scale=width / (float)(maxPhotoWidth - AndroidUtilities.dp(2));
                width/=scale;
                height/=scale;
                if (site_name == null || site_name != null && !site_name.toLowerCase().equals("instagram") && documentAttachType == 0) {
                  if (height > AndroidUtilities.displaySize.y / 3) {
                    height=AndroidUtilities.displaySize.y / 3;
                  }
                }
 else {
                  if (height > AndroidUtilities.displaySize.y / 2) {
                    height=AndroidUtilities.displaySize.y / 2;
                  }
                }
                if (imageBackgroundSideColor != 0) {
                  scale=height / (float)AndroidUtilities.dp(160);
                  width/=scale;
                  height/=scale;
                }
                if (height < AndroidUtilities.dp(60)) {
                  height=AndroidUtilities.dp(60);
                }
              }
            }
            if (isSmallImage) {
              if (AndroidUtilities.dp(50) + additionalHeight > linkPreviewHeight) {
                totalHeight+=AndroidUtilities.dp(50) + additionalHeight - linkPreviewHeight + AndroidUtilities.dp(8);
                linkPreviewHeight=AndroidUtilities.dp(50) + additionalHeight;
              }
              linkPreviewHeight-=AndroidUtilities.dp(8);
            }
 else {
              totalHeight+=height + AndroidUtilities.dp(12);
              linkPreviewHeight+=height;
            }
            if (documentAttachType == DOCUMENT_ATTACH_TYPE_WALLPAPER && imageBackgroundSideColor == 0) {
              photoImage.setImageCoords(0,0,Math.max(maxChildWidth - AndroidUtilities.dp(13),width),height);
            }
 else {
              photoImage.setImageCoords(0,0,width,height);
            }
            currentPhotoFilter=String.format(Locale.US,"%d_%d",width,height);
            currentPhotoFilterThumb=String.format(Locale.US,"%d_%d_b",width,height);
            if (webDocument != null) {
              photoImage.setImage(ImageLocation.getForWebFile(webDocument),currentPhotoFilter,null,null,webDocument.size,null,messageObject,1);
            }
 else {
              if (documentAttachType == DOCUMENT_ATTACH_TYPE_WALLPAPER) {
                if (messageObject.mediaExists) {
                  photoImage.setImage(ImageLocation.getForDocument(documentAttach),currentPhotoFilter,ImageLocation.getForDocument(currentPhotoObject,document),"b1",0,"jpg",messageObject,1);
                }
 else {
                  photoImage.setImage(null,null,ImageLocation.getForDocument(currentPhotoObject,document),"b1",0,"jpg",messageObject,1);
                }
              }
 else               if (documentAttachType == DOCUMENT_ATTACH_TYPE_STICKER) {
                photoImage.setImage(ImageLocation.getForDocument(documentAttach),currentPhotoFilter,ImageLocation.getForDocument(currentPhotoObject,documentAttach),"b1",documentAttach.size,"webp",messageObject,1);
              }
 else               if (documentAttachType == DOCUMENT_ATTACH_TYPE_VIDEO) {
                photoImage.setNeedsQualityThumb(true);
                photoImage.setShouldGenerateQualityThumb(true);
                if (SharedConfig.autoplayVideo && (currentMessageObject.mediaExists || messageObject.canStreamVideo() && DownloadController.getInstance(currentAccount).canDownloadMedia(currentMessageObject))) {
                  photoImage.setAllowDecodeSingleFrame(true);
                  photoImage.setAllowStartAnimation(true);
                  photoImage.startAnimation();
                  photoImage.setImage(ImageLocation.getForDocument(documentAttach),ImageLoader.AUTOPLAY_FILTER,ImageLocation.getForDocument(currentPhotoObject,documentAttach),currentPhotoFilter,ImageLocation.getForDocument(currentPhotoObjectThumb,documentAttach),currentPhotoFilterThumb,null,documentAttach.size,null,messageObject,0);
                  autoPlayingMedia=true;
                }
 else {
                  if (currentPhotoObjectThumb != null) {
                    photoImage.setImage(ImageLocation.getForDocument(currentPhotoObject,documentAttach),currentPhotoFilter,ImageLocation.getForDocument(currentPhotoObjectThumb,documentAttach),currentPhotoFilterThumb,0,null,messageObject,0);
                  }
 else {
                    photoImage.setImage(null,null,ImageLocation.getForDocument(currentPhotoObject,documentAttach),currentPhotoObject instanceof TLRPC.TL_photoStrippedSize || "s".equals(currentPhotoObject.type) ? currentPhotoFilterThumb : currentPhotoFilter,0,null,messageObject,0);
                  }
                }
              }
 else               if (documentAttachType == DOCUMENT_ATTACH_TYPE_GIF || documentAttachType == DOCUMENT_ATTACH_TYPE_ROUND) {
                photoImage.setAllowDecodeSingleFrame(true);
                String fileName=FileLoader.getAttachFileName(document);
                boolean autoDownload=false;
                if (MessageObject.isRoundVideoDocument(document)) {
                  photoImage.setRoundRadius(AndroidUtilities.roundMessageSize / 2);
                  autoDownload=DownloadController.getInstance(currentAccount).canDownloadMedia(currentMessageObject);
                }
 else                 if (MessageObject.isGifDocument(document)) {
                  autoDownload=DownloadController.getInstance(currentAccount).canDownloadMedia(currentMessageObject);
                }
                String filter=currentPhotoObject instanceof TLRPC.TL_photoStrippedSize || "s".equals(currentPhotoObject.type) ? currentPhotoFilterThumb : currentPhotoFilter;
                if (messageObject.mediaExists || autoDownload) {
                  autoPlayingMedia=true;
                  photoImage.setImage(ImageLocation.getForDocument(document),document.size < 1024 * 32 ? null : ImageLoader.AUTOPLAY_FILTER,ImageLocation.getForDocument(currentPhotoObject,documentAttach),filter,ImageLocation.getForDocument(currentPhotoObjectThumb,documentAttach),currentPhotoFilterThumb,null,document.size,null,messageObject,0);
                }
 else {
                  photoImage.setImage(null,null,ImageLocation.getForDocument(currentPhotoObject,documentAttach),filter,0,null,currentMessageObject,0);
                }
              }
 else {
                boolean photoExist=messageObject.mediaExists;
                String fileName=FileLoader.getAttachFileName(currentPhotoObject);
                if (hasGamePreview || photoExist || DownloadController.getInstance(currentAccount).canDownloadMedia(currentMessageObject) || FileLoader.getInstance(currentAccount).isLoadingFile(fileName)) {
                  photoNotSet=false;
                  photoImage.setImage(ImageLocation.getForObject(currentPhotoObject,photoParentObject),currentPhotoFilter,ImageLocation.getForObject(currentPhotoObjectThumb,photoParentObject),currentPhotoFilterThumb,0,null,messageObject,0);
                }
 else {
                  photoNotSet=true;
                  if (currentPhotoObjectThumb != null) {
                    photoImage.setImage(null,null,ImageLocation.getForObject(currentPhotoObjectThumb,photoParentObject),String.format(Locale.US,"%d_%d_b",width,height),0,null,messageObject,0);
                  }
 else {
                    photoImage.setImageBitmap((Drawable)null);
                  }
                }
              }
            }
            drawPhotoImage=true;
            if (type != null && type.equals("video") && duration != 0) {
              int minutes=duration / 60;
              int seconds=duration - minutes * 60;
              String str=String.format("%d:%02d",minutes,seconds);
              durationWidth=(int)Math.ceil(Theme.chat_durationPaint.measureText(str));
              videoInfoLayout=new StaticLayout(str,Theme.chat_durationPaint,durationWidth,Layout.Alignment.ALIGN_NORMAL,1.0f,0.0f,false);
            }
 else             if (hasGamePreview) {
              String str=LocaleController.getString("AttachGame",R.string.AttachGame).toUpperCase();
              durationWidth=(int)Math.ceil(Theme.chat_gamePaint.measureText(str));
              videoInfoLayout=new StaticLayout(str,Theme.chat_gamePaint,durationWidth,Layout.Alignment.ALIGN_NORMAL,1.0f,0.0f,false);
            }
          }
 else {
            photoImage.setImageBitmap((Drawable)null);
            linkPreviewHeight-=AndroidUtilities.dp(6);
            totalHeight+=AndroidUtilities.dp(4);
          }
          if (hasInvoicePreview) {
            CharSequence str;
            if ((messageObject.messageOwner.media.flags & 4) != 0) {
              str=LocaleController.getString("PaymentReceipt",R.string.PaymentReceipt).toUpperCase();
            }
 else {
              if (messageObject.messageOwner.media.test) {
                str=LocaleController.getString("PaymentTestInvoice",R.string.PaymentTestInvoice).toUpperCase();
              }
 else {
                str=LocaleController.getString("PaymentInvoice",R.string.PaymentInvoice).toUpperCase();
              }
            }
            String price=LocaleController.getInstance().formatCurrencyString(messageObject.messageOwner.media.total_amount,messageObject.messageOwner.media.currency);
            SpannableStringBuilder stringBuilder=new SpannableStringBuilder(price + " " + str);
            stringBuilder.setSpan(new TypefaceSpan(AndroidUtilities.getTypeface("fonts/rmedium.ttf")),0,price.length(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            durationWidth=(int)Math.ceil(Theme.chat_shipmentPaint.measureText(stringBuilder,0,stringBuilder.length()));
            videoInfoLayout=new StaticLayout(stringBuilder,Theme.chat_shipmentPaint,durationWidth + AndroidUtilities.dp(10),Layout.Alignment.ALIGN_NORMAL,1.0f,0.0f,false);
            if (!drawPhotoImage) {
              totalHeight+=AndroidUtilities.dp(6);
              int timeWidthTotal=timeWidth + AndroidUtilities.dp(14 + (messageObject.isOutOwner() ? 20 : 0));
              if (durationWidth + timeWidthTotal > maxWidth) {
                maxChildWidth=Math.max(durationWidth,maxChildWidth);
                totalHeight+=AndroidUtilities.dp(12);
              }
 else {
                maxChildWidth=Math.max(durationWidth + timeWidthTotal,maxChildWidth);
              }
            }
          }
          if (hasGamePreview && messageObject.textHeight != 0) {
            linkPreviewHeight+=messageObject.textHeight + AndroidUtilities.dp(6);
            totalHeight+=AndroidUtilities.dp(4);
          }
          calcBackgroundWidth(maxWidth,timeMore,maxChildWidth);
        }
        createInstantViewButton();
      }
 else {
        photoImage.setImageBitmap((Drawable)null);
        calcBackgroundWidth(maxWidth,timeMore,maxChildWidth);
      }
    }
 else     if (messageObject.type == 16) {
      drawName=false;
      drawForwardedName=false;
      drawPhotoImage=false;
      if (AndroidUtilities.isTablet()) {
        backgroundWidth=Math.min(AndroidUtilities.getMinTabletSide() - AndroidUtilities.dp(isChat && messageObject.needDrawAvatar() && !messageObject.isOutOwner() ? 102 : 50),AndroidUtilities.dp(270));
      }
 else {
        backgroundWidth=Math.min(AndroidUtilities.displaySize.x - AndroidUtilities.dp(isChat && messageObject.needDrawAvatar() && !messageObject.isOutOwner() ? 102 : 50),AndroidUtilities.dp(270));
      }
      availableTimeWidth=backgroundWidth - AndroidUtilities.dp(31);
      int maxWidth=getMaxNameWidth() - AndroidUtilities.dp(50);
      if (maxWidth < 0) {
        maxWidth=AndroidUtilities.dp(10);
      }
      String text;
      String time=LocaleController.getInstance().formatterDay.format((long)(messageObject.messageOwner.date) * 1000);
      TLRPC.TL_messageActionPhoneCall call=(TLRPC.TL_messageActionPhoneCall)messageObject.messageOwner.action;
      boolean isMissed=call.reason instanceof TLRPC.TL_phoneCallDiscardReasonMissed;
      if (messageObject.isOutOwner()) {
        if (isMissed) {
          text=LocaleController.getString("CallMessageOutgoingMissed",R.string.CallMessageOutgoingMissed);
        }
 else {
          text=LocaleController.getString("CallMessageOutgoing",R.string.CallMessageOutgoing);
        }
      }
 else {
        if (isMissed) {
          text=LocaleController.getString("CallMessageIncomingMissed",R.string.CallMessageIncomingMissed);
        }
 else         if (call.reason instanceof TLRPC.TL_phoneCallDiscardReasonBusy) {
          text=LocaleController.getString("CallMessageIncomingDeclined",R.string.CallMessageIncomingDeclined);
        }
 else {
          text=LocaleController.getString("CallMessageIncoming",R.string.CallMessageIncoming);
        }
      }
      if (call.duration > 0) {
        time+=", " + LocaleController.formatCallDuration(call.duration);
      }
      titleLayout=new StaticLayout(TextUtils.ellipsize(text,Theme.chat_audioTitlePaint,maxWidth,TextUtils.TruncateAt.END),Theme.chat_audioTitlePaint,maxWidth + AndroidUtilities.dp(2),Layout.Alignment.ALIGN_NORMAL,1.0f,0.0f,false);
      docTitleLayout=new StaticLayout(TextUtils.ellipsize(time,Theme.chat_contactPhonePaint,maxWidth,TextUtils.TruncateAt.END),Theme.chat_contactPhonePaint,maxWidth + AndroidUtilities.dp(2),Layout.Alignment.ALIGN_NORMAL,1.0f,0.0f,false);
      setMessageObjectInternal(messageObject);
      totalHeight=AndroidUtilities.dp(65) + namesOffset;
      if (drawPinnedTop) {
        namesOffset-=AndroidUtilities.dp(1);
      }
    }
 else     if (messageObject.type == 12) {
      drawName=false;
      drawForwardedName=true;
      drawPhotoImage=true;
      photoImage.setRoundRadius(AndroidUtilities.dp(22));
      if (AndroidUtilities.isTablet()) {
        backgroundWidth=Math.min(AndroidUtilities.getMinTabletSide() - AndroidUtilities.dp(isChat && messageObject.needDrawAvatar() && !messageObject.isOutOwner() ? 102 : 50),AndroidUtilities.dp(270));
      }
 else {
        backgroundWidth=Math.min(AndroidUtilities.displaySize.x - AndroidUtilities.dp(isChat && messageObject.needDrawAvatar() && !messageObject.isOutOwner() ? 102 : 50),AndroidUtilities.dp(270));
      }
      availableTimeWidth=backgroundWidth - AndroidUtilities.dp(31);
      int uid=messageObject.messageOwner.media.user_id;
      TLRPC.User user=MessagesController.getInstance(currentAccount).getUser(uid);
      int maxWidth=getMaxNameWidth() - AndroidUtilities.dp(80);
      if (maxWidth < 0) {
        maxWidth=AndroidUtilities.dp(10);
      }
      if (user != null) {
        contactAvatarDrawable.setInfo(user);
      }
      photoImage.setImage(ImageLocation.getForUser(user,false),"50_50",user != null ? contactAvatarDrawable : Theme.chat_contactDrawable[messageObject.isOutOwner() ? 1 : 0],null,messageObject,0);
      CharSequence phone;
      if (!TextUtils.isEmpty(messageObject.vCardData)) {
        phone=messageObject.vCardData;
        drawInstantView=true;
        drawInstantViewType=5;
      }
 else {
        if (user != null && !TextUtils.isEmpty(user.phone)) {
          phone=PhoneFormat.getInstance().format("+" + user.phone);
        }
 else {
          phone=messageObject.messageOwner.media.phone_number;
          if (!TextUtils.isEmpty(phone)) {
            phone=PhoneFormat.getInstance().format((String)phone);
          }
 else {
            phone=LocaleController.getString("NumberUnknown",R.string.NumberUnknown);
          }
        }
      }
      CharSequence currentNameString=ContactsController.formatName(messageObject.messageOwner.media.first_name,messageObject.messageOwner.media.last_name).replace('\n',' ');
      if (currentNameString.length() == 0) {
        currentNameString=messageObject.messageOwner.media.phone_number;
        if (currentNameString == null) {
          currentNameString="";
        }
      }
      titleLayout=new StaticLayout(TextUtils.ellipsize(currentNameString,Theme.chat_contactNamePaint,maxWidth,TextUtils.TruncateAt.END),Theme.chat_contactNamePaint,maxWidth + AndroidUtilities.dp(4),Layout.Alignment.ALIGN_NORMAL,1.0f,0.0f,false);
      docTitleLayout=new StaticLayout(phone,Theme.chat_contactPhonePaint,maxWidth + AndroidUtilities.dp(2),Layout.Alignment.ALIGN_NORMAL,1.0f,AndroidUtilities.dp(1),false);
      setMessageObjectInternal(messageObject);
      if (drawForwardedName && messageObject.needDrawForwarded() && (currentPosition == null || currentPosition.minY == 0)) {
        namesOffset+=AndroidUtilities.dp(5);
      }
 else       if (drawNameLayout && messageObject.messageOwner.reply_to_msg_id == 0) {
        namesOffset+=AndroidUtilities.dp(7);
      }
      totalHeight=AndroidUtilities.dp(70 - 15) + namesOffset + docTitleLayout.getHeight();
      if (drawPinnedTop) {
        namesOffset-=AndroidUtilities.dp(1);
      }
      if (drawInstantView) {
        createInstantViewButton();
      }
 else {
        if (docTitleLayout.getLineCount() > 0) {
          int timeLeft=backgroundWidth - AndroidUtilities.dp(40 + 18 + 44 + 8) - (int)Math.ceil(docTitleLayout.getLineWidth(docTitleLayout.getLineCount() - 1));
          if (timeLeft < timeWidth) {
            totalHeight+=AndroidUtilities.dp(8);
          }
        }
      }
    }
 else     if (messageObject.type == 2) {
      drawForwardedName=true;
      if (AndroidUtilities.isTablet()) {
        backgroundWidth=Math.min(AndroidUtilities.getMinTabletSide() - AndroidUtilities.dp(isChat && messageObject.needDrawAvatar() && !messageObject.isOutOwner() ? 102 : 50),AndroidUtilities.dp(270));
      }
 else {
        backgroundWidth=Math.min(AndroidUtilities.displaySize.x - AndroidUtilities.dp(isChat && messageObject.needDrawAvatar() && !messageObject.isOutOwner() ? 102 : 50),AndroidUtilities.dp(270));
      }
      createDocumentLayout(backgroundWidth,messageObject);
      setMessageObjectInternal(messageObject);
      totalHeight=AndroidUtilities.dp(70) + namesOffset;
      if (drawPinnedTop) {
        namesOffset-=AndroidUtilities.dp(1);
      }
    }
 else     if (messageObject.type == 14) {
      if (AndroidUtilities.isTablet()) {
        backgroundWidth=Math.min(AndroidUtilities.getMinTabletSide() - AndroidUtilities.dp(isChat && messageObject.needDrawAvatar() && !messageObject.isOutOwner() ? 102 : 50),AndroidUtilities.dp(270));
      }
 else {
        backgroundWidth=Math.min(AndroidUtilities.displaySize.x - AndroidUtilities.dp(isChat && messageObject.needDrawAvatar() && !messageObject.isOutOwner() ? 102 : 50),AndroidUtilities.dp(270));
      }
      createDocumentLayout(backgroundWidth,messageObject);
      setMessageObjectInternal(messageObject);
      totalHeight=AndroidUtilities.dp(82) + namesOffset;
      if (drawPinnedTop) {
        namesOffset-=AndroidUtilities.dp(1);
      }
    }
 else     if (messageObject.type == MessageObject.TYPE_POLL) {
      createSelectorDrawable();
      drawName=true;
      drawForwardedName=true;
      drawPhotoImage=false;
      int maxWidth=availableTimeWidth=Math.min(AndroidUtilities.dp(500),messageObject.getMaxMessageTextWidth());
      backgroundWidth=maxWidth + AndroidUtilities.dp(31);
      availableTimeWidth=AndroidUtilities.dp(120);
      measureTime(messageObject);
      TLRPC.TL_messageMediaPoll media=(TLRPC.TL_messageMediaPoll)messageObject.messageOwner.media;
      pollClosed=media.poll.closed;
      pollVoted=messageObject.isVoted();
      titleLayout=new StaticLayout(Emoji.replaceEmoji(media.poll.question,Theme.chat_audioTitlePaint.getFontMetricsInt(),AndroidUtilities.dp(16),false),Theme.chat_audioTitlePaint,maxWidth + AndroidUtilities.dp(2),Layout.Alignment.ALIGN_NORMAL,1.0f,0.0f,false);
      boolean titleRtl=false;
      if (titleLayout != null) {
        for (int a=0, N=titleLayout.getLineCount(); a < N; a++) {
          if (titleLayout.getLineLeft(a) != 0) {
            titleRtl=true;
            break;
          }
        }
      }
      docTitleLayout=new StaticLayout(TextUtils.ellipsize(media.poll.closed ? LocaleController.getString("FinalResults",R.string.FinalResults) : LocaleController.getString("AnonymousPoll",R.string.AnonymousPoll),Theme.chat_timePaint,maxWidth,TextUtils.TruncateAt.END),Theme.chat_timePaint,maxWidth + AndroidUtilities.dp(2),Layout.Alignment.ALIGN_NORMAL,1.0f,0.0f,false);
      if (docTitleLayout != null && docTitleLayout.getLineCount() > 0) {
        if (titleRtl && !LocaleController.isRTL) {
          docTitleOffsetX=(int)Math.ceil(maxWidth - docTitleLayout.getLineWidth(0));
        }
 else         if (!titleRtl && LocaleController.isRTL) {
          docTitleOffsetX=-(int)Math.ceil(docTitleLayout.getLineLeft(0));
        }
      }
      int w=maxWidth - timeWidth - AndroidUtilities.dp(messageObject.isOutOwner() ? 28 : 8);
      infoLayout=new StaticLayout(TextUtils.ellipsize(media.results.total_voters == 0 ? LocaleController.getString("NoVotes",R.string.NoVotes) : LocaleController.formatPluralString("Vote",media.results.total_voters),Theme.chat_livePaint,w,TextUtils.TruncateAt.END),Theme.chat_livePaint,w,Layout.Alignment.ALIGN_NORMAL,1.0f,0.0f,false);
      infoX=(int)Math.ceil(infoLayout != null && infoLayout.getLineCount() > 0 ? -infoLayout.getLineLeft(0) : 0);
      lastPoll=media.poll;
      lastPollResults=media.results.results;
      lastPollResultsVoters=media.results.total_voters;
      int maxVote=0;
      if (!animatePollAnswer && pollVoteInProgress) {
        performHapticFeedback(HapticFeedbackConstants.KEYBOARD_TAP,HapticFeedbackConstants.FLAG_IGNORE_GLOBAL_SETTING);
      }
      animatePollAnswerAlpha=animatePollAnswer=attachedToWindow && (pollVoteInProgress || pollUnvoteInProgress);
      ArrayList<PollButton> previousPollButtons=null;
      ArrayList<PollButton> sortedPollButtons=new ArrayList<>();
      if (!pollButtons.isEmpty()) {
        previousPollButtons=new ArrayList<>(pollButtons);
        pollButtons.clear();
        if (!animatePollAnswer) {
          animatePollAnswer=attachedToWindow && (pollVoted || pollClosed);
        }
        if (pollAnimationProgress > 0 && pollAnimationProgress < 1.0f) {
          for (int b=0, N2=previousPollButtons.size(); b < N2; b++) {
            PollButton button=previousPollButtons.get(b);
            button.percent=(int)Math.ceil(button.prevPercent + (button.percent - button.prevPercent) * pollAnimationProgress);
            button.percentProgress=button.prevPercentProgress + (button.percentProgress - button.prevPercentProgress) * pollAnimationProgress;
          }
        }
      }
      pollAnimationProgress=animatePollAnswer ? 0.0f : 1.0f;
      byte[] votingFor;
      if (!animatePollAnswerAlpha) {
        pollVoteInProgress=false;
        pollVoteInProgressNum=-1;
        votingFor=SendMessagesHelper.getInstance(currentAccount).isSendingVote(currentMessageObject);
      }
 else {
        votingFor=null;
      }
      int height=titleLayout != null ? titleLayout.getHeight() : 0;
      int restPercent=100;
      boolean hasDifferent=false;
      int previousPercent=0;
      for (int a=0, N=media.poll.answers.size(); a < N; a++) {
        PollButton button=new PollButton();
        button.answer=media.poll.answers.get(a);
        button.title=new StaticLayout(Emoji.replaceEmoji(button.answer.text,Theme.chat_audioPerformerPaint.getFontMetricsInt(),AndroidUtilities.dp(15),false),Theme.chat_audioPerformerPaint,maxWidth - AndroidUtilities.dp(33),Layout.Alignment.ALIGN_NORMAL,1.0f,0.0f,false);
        button.y=height + AndroidUtilities.dp(52);
        button.height=button.title.getHeight();
        pollButtons.add(button);
        sortedPollButtons.add(button);
        height+=button.height + AndroidUtilities.dp(26);
        if (!media.results.results.isEmpty()) {
          for (int b=0, N2=media.results.results.size(); b < N2; b++) {
            TLRPC.TL_pollAnswerVoters answer=media.results.results.get(b);
            if (Arrays.equals(button.answer.option,answer.option)) {
              if ((pollVoted || pollClosed) && media.results.total_voters > 0) {
                button.decimal=100 * (answer.voters / (float)media.results.total_voters);
                button.percent=(int)button.decimal;
                button.decimal-=button.percent;
              }
 else {
                button.percent=0;
                button.decimal=0;
              }
              if (previousPercent == 0) {
                previousPercent=button.percent;
              }
 else               if (button.percent != 0 && previousPercent != button.percent) {
                hasDifferent=true;
              }
              restPercent-=button.percent;
              maxVote=Math.max(button.percent,maxVote);
              break;
            }
          }
        }
        if (previousPollButtons != null) {
          for (int b=0, N2=previousPollButtons.size(); b < N2; b++) {
            PollButton prevButton=previousPollButtons.get(b);
            if (Arrays.equals(button.answer.option,prevButton.answer.option)) {
              button.prevPercent=prevButton.percent;
              button.prevPercentProgress=prevButton.percentProgress;
              break;
            }
          }
        }
        if (votingFor != null && Arrays.equals(button.answer.option,votingFor)) {
          pollVoteInProgressNum=a;
          pollVoteInProgress=true;
          votingFor=null;
        }
      }
      if (hasDifferent && restPercent != 0) {
        Collections.sort(sortedPollButtons,(o1,o2) -> {
          if (o1.decimal > o2.decimal) {
            return -1;
          }
 else           if (o1.decimal < o2.decimal) {
            return 1;
          }
          return 0;
        }
);
        for (int a=0, N=Math.min(restPercent,sortedPollButtons.size()); a < N; a++) {
          sortedPollButtons.get(a).percent+=1;
        }
      }
      int width=backgroundWidth - AndroidUtilities.dp(76);
      for (int b=0, N2=pollButtons.size(); b < N2; b++) {
        PollButton button=pollButtons.get(b);
        button.percentProgress=Math.max(AndroidUtilities.dp(5) / (float)width,maxVote != 0 ? button.percent / (float)maxVote : 0);
      }
      setMessageObjectInternal(messageObject);
      totalHeight=AndroidUtilities.dp(46 + 27) + namesOffset + height;
      if (drawPinnedTop) {
        namesOffset-=AndroidUtilities.dp(1);
      }
    }
 else {
      drawForwardedName=messageObject.messageOwner.fwd_from != null && !messageObject.isAnyKindOfSticker();
      mediaBackground=messageObject.type != 9;
      drawImageButton=true;
      drawPhotoImage=true;
      int photoWidth=0;
      int photoHeight=0;
      int additionHeight=0;
      if (messageObject.gifState != 2 && !SharedConfig.autoplayGifs && (messageObject.type == 8 || messageObject.type == MessageObject.TYPE_ROUND_VIDEO)) {
        messageObject.gifState=1;
      }
      photoImage.setAllowDecodeSingleFrame(true);
      if (messageObject.isVideo()) {
        photoImage.setAllowStartAnimation(true);
      }
 else       if (messageObject.isRoundVideo()) {
        MessageObject playingMessage=MediaController.getInstance().getPlayingMessageObject();
        photoImage.setAllowStartAnimation(playingMessage == null || !playingMessage.isRoundVideo());
      }
 else {
        photoImage.setAllowStartAnimation(messageObject.gifState == 0);
      }
      photoImage.setForcePreview(messageObject.needDrawBluredPreview());
      if (messageObject.type == 9) {
        if (AndroidUtilities.isTablet()) {
          backgroundWidth=Math.min(AndroidUtilities.getMinTabletSide() - AndroidUtilities.dp(isChat && messageObject.needDrawAvatar() && !messageObject.isOutOwner() ? 102 : 50),AndroidUtilities.dp(300));
        }
 else {
          backgroundWidth=Math.min(AndroidUtilities.displaySize.x - AndroidUtilities.dp(isChat && messageObject.needDrawAvatar() && !messageObject.isOutOwner() ? 102 : 50),AndroidUtilities.dp(300));
        }
        if (checkNeedDrawShareButton(messageObject)) {
          backgroundWidth-=AndroidUtilities.dp(20);
        }
        int maxTextWidth=0;
        int maxWidth=backgroundWidth - AndroidUtilities.dp(86 + 52);
        int widthForCaption=0;
        createDocumentLayout(maxWidth,messageObject);
        if (!TextUtils.isEmpty(messageObject.caption)) {
          try {
            currentCaption=messageObject.caption;
            int width=backgroundWidth - AndroidUtilities.dp(31);
            widthForCaption=width - AndroidUtilities.dp(10);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
              captionLayout=StaticLayout.Builder.obtain(messageObject.caption,0,messageObject.caption.length(),Theme.chat_msgTextPaint,widthForCaption).setBreakStrategy(StaticLayout.BREAK_STRATEGY_HIGH_QUALITY).setHyphenationFrequency(StaticLayout.HYPHENATION_FREQUENCY_NONE).setAlignment(Layout.Alignment.ALIGN_NORMAL).build();
            }
 else {
              captionLayout=new StaticLayout(messageObject.caption,Theme.chat_msgTextPaint,widthForCaption,Layout.Alignment.ALIGN_NORMAL,1.0f,0.0f,false);
            }
          }
 catch (          Exception e) {
            FileLog.e(e);
          }
        }
        if (docTitleLayout != null) {
          for (int a=0, N=docTitleLayout.getLineCount(); a < N; a++) {
            maxTextWidth=Math.max(maxTextWidth,(int)Math.ceil(docTitleLayout.getLineWidth(a) + docTitleLayout.getLineLeft(a)) + AndroidUtilities.dp(86 + (drawPhotoImage ? 52 : 22)));
          }
        }
        if (infoLayout != null) {
          for (int a=0, N=infoLayout.getLineCount(); a < N; a++) {
            maxTextWidth=Math.max(maxTextWidth,(int)Math.ceil(infoLayout.getLineWidth(a)) + AndroidUtilities.dp(86 + (drawPhotoImage ? 52 : 22)));
          }
        }
        if (captionLayout != null) {
          for (int a=0, N=captionLayout.getLineCount(); a < N; a++) {
            int w=(int)Math.ceil(Math.min(widthForCaption,captionLayout.getLineWidth(a) + captionLayout.getLineLeft(a))) + AndroidUtilities.dp(31);
            if (w > maxTextWidth) {
              maxTextWidth=w;
            }
          }
        }
        if (maxTextWidth > 0) {
          backgroundWidth=maxTextWidth;
          maxWidth=maxTextWidth - AndroidUtilities.dp(31);
        }
        if (drawPhotoImage) {
          photoWidth=AndroidUtilities.dp(86);
          photoHeight=AndroidUtilities.dp(86);
        }
 else {
          photoWidth=AndroidUtilities.dp(56);
          photoHeight=AndroidUtilities.dp(56);
          if (docTitleLayout != null && docTitleLayout.getLineCount() > 1) {
            photoHeight+=(docTitleLayout.getLineCount() - 1) * AndroidUtilities.dp(16);
          }
        }
        availableTimeWidth=maxWidth;
        if (!drawPhotoImage && TextUtils.isEmpty(messageObject.caption) && infoLayout != null) {
          int lineCount=infoLayout.getLineCount();
          measureTime(messageObject);
          int timeLeft=backgroundWidth - AndroidUtilities.dp(40 + 18 + 56 + 8) - (int)Math.ceil(infoLayout.getLineWidth(0));
          if (timeLeft < timeWidth) {
            photoHeight+=AndroidUtilities.dp(12);
          }
 else           if (lineCount == 1) {
            photoHeight+=AndroidUtilities.dp(4);
          }
        }
      }
 else       if (messageObject.type == 4) {
        TLRPC.GeoPoint point=messageObject.messageOwner.media.geo;
        double lat=point.lat;
        double lon=point._long;
        if (messageObject.messageOwner.media instanceof TLRPC.TL_messageMediaGeoLive) {
          if (AndroidUtilities.isTablet()) {
            backgroundWidth=Math.min(AndroidUtilities.getMinTabletSide() - AndroidUtilities.dp(isChat && messageObject.needDrawAvatar() && !messageObject.isOutOwner() ? 102 : 50),AndroidUtilities.dp(252 + 37));
          }
 else {
            backgroundWidth=Math.min(AndroidUtilities.displaySize.x - AndroidUtilities.dp(isChat && messageObject.needDrawAvatar() && !messageObject.isOutOwner() ? 102 : 50),AndroidUtilities.dp(252 + 37));
          }
          backgroundWidth-=AndroidUtilities.dp(4);
          if (checkNeedDrawShareButton(messageObject)) {
            backgroundWidth-=AndroidUtilities.dp(20);
          }
          int maxWidth=backgroundWidth - AndroidUtilities.dp(37);
          availableTimeWidth=maxWidth;
          maxWidth-=AndroidUtilities.dp(54);
          photoWidth=backgroundWidth - AndroidUtilities.dp(17);
          photoHeight=AndroidUtilities.dp(195);
          int offset=268435456;
          double rad=offset / Math.PI;
          double y=Math.round(offset - rad * Math.log((1 + Math.sin(lat * Math.PI / 180.0)) / (1 - Math.sin(lat * Math.PI / 180.0))) / 2) - (AndroidUtilities.dp(10.3f) << (21 - 15));
          lat=(Math.PI / 2.0 - 2 * Math.atan(Math.exp((y - offset) / rad))) * 180.0 / Math.PI;
          currentUrl=AndroidUtilities.formapMapUrl(currentAccount,lat,lon,(int)(photoWidth / AndroidUtilities.density),(int)(photoHeight / AndroidUtilities.density),false,15);
          currentWebFile=WebFile.createWithGeoPoint(lat,lon,point.access_hash,(int)(photoWidth / AndroidUtilities.density),(int)(photoHeight / AndroidUtilities.density),15,Math.min(2,(int)Math.ceil(AndroidUtilities.density)));
          if (!(locationExpired=isCurrentLocationTimeExpired(messageObject))) {
            photoImage.setCrossfadeWithOldImage(true);
            mediaBackground=false;
            additionHeight=AndroidUtilities.dp(56);
            AndroidUtilities.runOnUIThread(invalidateRunnable,1000);
            scheduledInvalidate=true;
          }
 else {
            backgroundWidth-=AndroidUtilities.dp(9);
          }
          docTitleLayout=new StaticLayout(TextUtils.ellipsize(LocaleController.getString("AttachLiveLocation",R.string.AttachLiveLocation),Theme.chat_locationTitlePaint,maxWidth,TextUtils.TruncateAt.END),Theme.chat_locationTitlePaint,maxWidth,Layout.Alignment.ALIGN_NORMAL,1.0f,0.0f,false);
          updateCurrentUserAndChat();
          if (currentUser != null) {
            contactAvatarDrawable.setInfo(currentUser);
            locationImageReceiver.setImage(ImageLocation.getForUser(currentUser,false),"50_50",contactAvatarDrawable,null,currentUser,0);
          }
 else           if (currentChat != null) {
            if (currentChat.photo != null) {
              currentPhoto=currentChat.photo.photo_small;
            }
            contactAvatarDrawable.setInfo(currentChat);
            locationImageReceiver.setImage(ImageLocation.getForChat(currentChat,false),"50_50",contactAvatarDrawable,null,currentChat,0);
          }
 else {
            locationImageReceiver.setImage(null,null,contactAvatarDrawable,null,null,0);
          }
          infoLayout=new StaticLayout(LocaleController.formatLocationUpdateDate(messageObject.messageOwner.edit_date != 0 ? messageObject.messageOwner.edit_date : messageObject.messageOwner.date),Theme.chat_locationAddressPaint,maxWidth,Layout.Alignment.ALIGN_NORMAL,1.0f,0.0f,false);
        }
 else         if (!TextUtils.isEmpty(messageObject.messageOwner.media.title)) {
          if (AndroidUtilities.isTablet()) {
            backgroundWidth=Math.min(AndroidUtilities.getMinTabletSide() - AndroidUtilities.dp(isChat && messageObject.needDrawAvatar() && !messageObject.isOutOwner() ? 102 : 50),AndroidUtilities.dp(252 + 37));
          }
 else {
            backgroundWidth=Math.min(AndroidUtilities.displaySize.x - AndroidUtilities.dp(isChat && messageObject.needDrawAvatar() && !messageObject.isOutOwner() ? 102 : 50),AndroidUtilities.dp(252 + 37));
          }
          backgroundWidth-=AndroidUtilities.dp(4);
          if (checkNeedDrawShareButton(messageObject)) {
            backgroundWidth-=AndroidUtilities.dp(20);
          }
          int maxWidth=backgroundWidth - AndroidUtilities.dp(34);
          availableTimeWidth=maxWidth;
          photoWidth=backgroundWidth - AndroidUtilities.dp(17);
          photoHeight=AndroidUtilities.dp(195);
          mediaBackground=false;
          currentUrl=AndroidUtilities.formapMapUrl(currentAccount,lat,lon,(int)(photoWidth / AndroidUtilities.density),(int)(photoHeight / AndroidUtilities.density),true,15);
          currentWebFile=WebFile.createWithGeoPoint(point,(int)(photoWidth / AndroidUtilities.density),(int)(photoHeight / AndroidUtilities.density),15,Math.min(2,(int)Math.ceil(AndroidUtilities.density)));
          docTitleLayout=StaticLayoutEx.createStaticLayout(messageObject.messageOwner.media.title,Theme.chat_locationTitlePaint,maxWidth,Layout.Alignment.ALIGN_NORMAL,1.0f,0.0f,false,TextUtils.TruncateAt.END,maxWidth,1);
          additionHeight+=AndroidUtilities.dp(50);
          int lineCount=docTitleLayout.getLineCount();
          if (!TextUtils.isEmpty(messageObject.messageOwner.media.address)) {
            infoLayout=StaticLayoutEx.createStaticLayout(messageObject.messageOwner.media.address,Theme.chat_locationAddressPaint,maxWidth,Layout.Alignment.ALIGN_NORMAL,1.0f,0.0f,false,TextUtils.TruncateAt.END,maxWidth,1);
            measureTime(messageObject);
            int timeLeft=backgroundWidth - (int)Math.ceil(infoLayout.getLineWidth(0)) - AndroidUtilities.dp(24);
            if (timeLeft < timeWidth + AndroidUtilities.dp(20 + (messageObject.isOutOwner() ? 20 : 0))) {
              additionHeight+=AndroidUtilities.dp(8);
            }
          }
 else {
            infoLayout=null;
          }
        }
 else {
          if (AndroidUtilities.isTablet()) {
            backgroundWidth=Math.min(AndroidUtilities.getMinTabletSide() - AndroidUtilities.dp(isChat && messageObject.needDrawAvatar() && !messageObject.isOutOwner() ? 102 : 50),AndroidUtilities.dp(252 + 37));
          }
 else {
            backgroundWidth=Math.min(AndroidUtilities.displaySize.x - AndroidUtilities.dp(isChat && messageObject.needDrawAvatar() && !messageObject.isOutOwner() ? 102 : 50),AndroidUtilities.dp(252 + 37));
          }
          backgroundWidth-=AndroidUtilities.dp(4);
          if (checkNeedDrawShareButton(messageObject)) {
            backgroundWidth-=AndroidUtilities.dp(20);
          }
          availableTimeWidth=backgroundWidth - AndroidUtilities.dp(34);
          photoWidth=backgroundWidth - AndroidUtilities.dp(8);
          photoHeight=AndroidUtilities.dp(195);
          currentUrl=AndroidUtilities.formapMapUrl(currentAccount,lat,lon,(int)(photoWidth / AndroidUtilities.density),(int)(photoHeight / AndroidUtilities.density),true,15);
          currentWebFile=WebFile.createWithGeoPoint(point,(int)(photoWidth / AndroidUtilities.density),(int)(photoHeight / AndroidUtilities.density),15,Math.min(2,(int)Math.ceil(AndroidUtilities.density)));
        }
        if ((int)messageObject.getDialogId() == 0) {
          if (SharedConfig.mapPreviewType == 0) {
            currentMapProvider=2;
          }
 else           if (SharedConfig.mapPreviewType == 1) {
            currentMapProvider=1;
          }
 else {
            currentMapProvider=-1;
          }
        }
 else {
          currentMapProvider=MessagesController.getInstance(messageObject.currentAccount).mapProvider;
        }
        if (currentMapProvider == -1) {
          photoImage.setImage(null,null,Theme.chat_locationDrawable[messageObject.isOutOwner() ? 1 : 0],null,messageObject,0);
        }
 else         if (currentMapProvider == 2) {
          if (currentWebFile != null) {
            photoImage.setImage(ImageLocation.getForWebFile(currentWebFile),null,Theme.chat_locationDrawable[messageObject.isOutOwner() ? 1 : 0],null,messageObject,0);
          }
        }
 else {
          if (currentMapProvider == 3 || currentMapProvider == 4) {
            ImageLoader.getInstance().addTestWebFile(currentUrl,currentWebFile);
            addedForTest=true;
          }
          if (currentUrl != null) {
            photoImage.setImage(currentUrl,null,Theme.chat_locationDrawable[messageObject.isOutOwner() ? 1 : 0],null,0);
          }
        }
      }
 else       if (messageObject.isAnyKindOfSticker()) {
        drawBackground=false;
        boolean isWebpSticker=messageObject.type == MessageObject.TYPE_STICKER;
        for (int a=0; a < messageObject.messageOwner.media.document.attributes.size(); a++) {
          TLRPC.DocumentAttribute attribute=messageObject.messageOwner.media.document.attributes.get(a);
          if (attribute instanceof TLRPC.TL_documentAttributeImageSize) {
            photoWidth=attribute.w;
            photoHeight=attribute.h;
          }
        }
        if (photoWidth == 0 && photoHeight == 0 && messageObject.isAnimatedSticker()) {
          photoWidth=photoHeight=512;
        }
        float maxHeight;
        float maxWidth;
        if (AndroidUtilities.isTablet()) {
          maxHeight=maxWidth=AndroidUtilities.getMinTabletSide() * 0.4f;
        }
 else {
          maxHeight=maxWidth=Math.min(AndroidUtilities.displaySize.x,AndroidUtilities.displaySize.y) * 0.5f;
        }
        if (photoWidth == 0) {
          photoHeight=(int)maxHeight;
          photoWidth=photoHeight + AndroidUtilities.dp(100);
        }
        photoHeight*=maxWidth / photoWidth;
        photoWidth=(int)maxWidth;
        if (photoHeight > maxHeight) {
          photoWidth*=maxHeight / photoHeight;
          photoHeight=(int)maxHeight;
        }
        documentAttachType=DOCUMENT_ATTACH_TYPE_STICKER;
        availableTimeWidth=photoWidth - AndroidUtilities.dp(14);
        backgroundWidth=photoWidth + AndroidUtilities.dp(12);
        currentPhotoObjectThumb=FileLoader.getClosestPhotoSizeWithSize(messageObject.photoThumbs,40);
        photoParentObject=messageObject.photoThumbsObject;
        if (messageObject.attachPathExists) {
          photoImage.setImage(ImageLocation.getForPath(messageObject.messageOwner.attachPath),String.format(Locale.US,"%d_%d",photoWidth,photoHeight),ImageLocation.getForObject(currentPhotoObjectThumb,photoParentObject),"b1",messageObject.messageOwner.media.document.size,isWebpSticker ? "webp" : null,messageObject,1);
        }
 else         if (messageObject.messageOwner.media.document.id != 0) {
          photoImage.setImage(ImageLocation.getForDocument(messageObject.messageOwner.media.document),String.format(Locale.US,"%d_%d",photoWidth,photoHeight),ImageLocation.getForObject(currentPhotoObjectThumb,photoParentObject),"b1",messageObject.messageOwner.media.document.size,isWebpSticker ? "webp" : null,messageObject,1);
        }
      }
 else {
        currentPhotoObject=FileLoader.getClosestPhotoSizeWithSize(messageObject.photoThumbs,AndroidUtilities.getPhotoSize());
        photoParentObject=messageObject.photoThumbsObject;
        int maxPhotoWidth;
        boolean useFullWidth=false;
        if (messageObject.type == MessageObject.TYPE_ROUND_VIDEO) {
          maxPhotoWidth=photoWidth=AndroidUtilities.roundMessageSize;
          documentAttach=messageObject.getDocument();
          documentAttachType=DOCUMENT_ATTACH_TYPE_ROUND;
        }
 else {
          if (AndroidUtilities.isTablet()) {
            maxPhotoWidth=photoWidth=(int)(AndroidUtilities.getMinTabletSide() * 0.7f);
          }
 else {
            if (currentPhotoObject != null && (messageObject.type == 1 || messageObject.type == 3 || messageObject.type == 8) && currentPhotoObject.w >= currentPhotoObject.h) {
              maxPhotoWidth=photoWidth=Math.min(AndroidUtilities.displaySize.x,AndroidUtilities.displaySize.y) - AndroidUtilities.dp(64);
              useFullWidth=true;
            }
 else {
              maxPhotoWidth=photoWidth=(int)(Math.min(AndroidUtilities.displaySize.x,AndroidUtilities.displaySize.y) * 0.7f);
            }
          }
        }
        photoHeight=photoWidth + AndroidUtilities.dp(100);
        if (!useFullWidth) {
          if (messageObject.type != 5 && checkNeedDrawShareButton(messageObject)) {
            maxPhotoWidth-=AndroidUtilities.dp(20);
            photoWidth-=AndroidUtilities.dp(20);
          }
          if (photoWidth > AndroidUtilities.getPhotoSize()) {
            photoWidth=AndroidUtilities.getPhotoSize();
          }
          if (photoHeight > AndroidUtilities.getPhotoSize()) {
            photoHeight=AndroidUtilities.getPhotoSize();
          }
        }
 else         if (isChat && messageObject.needDrawAvatar() && !messageObject.isOutOwner()) {
          photoWidth-=AndroidUtilities.dp(52);
        }
        boolean needQualityPreview=false;
        if (messageObject.type == 1) {
          updateSecretTimeText(messageObject);
          currentPhotoObjectThumb=FileLoader.getClosestPhotoSizeWithSize(messageObject.photoThumbs,40);
        }
 else         if (messageObject.type == 3 || messageObject.type == 8) {
          createDocumentLayout(0,messageObject);
          currentPhotoObjectThumb=FileLoader.getClosestPhotoSizeWithSize(messageObject.photoThumbs,40);
          updateSecretTimeText(messageObject);
          needQualityPreview=true;
        }
 else         if (messageObject.type == MessageObject.TYPE_ROUND_VIDEO) {
          currentPhotoObjectThumb=FileLoader.getClosestPhotoSizeWithSize(messageObject.photoThumbs,40);
          needQualityPreview=true;
        }
        int w;
        int h;
        if (messageObject.type == MessageObject.TYPE_ROUND_VIDEO) {
          w=h=AndroidUtilities.roundMessageSize;
        }
 else {
          TLRPC.PhotoSize size=currentPhotoObject != null ? currentPhotoObject : currentPhotoObjectThumb;
          int imageW=0;
          int imageH=0;
          if (size != null) {
            imageW=size.w;
            imageH=size.h;
          }
 else           if (documentAttach != null) {
            for (int a=0, N=documentAttach.attributes.size(); a < N; a++) {
              TLRPC.DocumentAttribute attribute=documentAttach.attributes.get(a);
              if (attribute instanceof TLRPC.TL_documentAttributeVideo) {
                imageW=attribute.w;
                imageH=attribute.h;
              }
            }
          }
          float scale=(float)imageW / (float)photoWidth;
          w=(int)(imageW / scale);
          h=(int)(imageH / scale);
          if (w == 0) {
            w=AndroidUtilities.dp(150);
          }
          if (h == 0) {
            h=AndroidUtilities.dp(150);
          }
          if (h > photoHeight) {
            float scale2=h;
            h=photoHeight;
            scale2/=h;
            w=(int)(w / scale2);
          }
 else           if (h < AndroidUtilities.dp(120)) {
            h=AndroidUtilities.dp(120);
            float hScale=(float)imageH / h;
            if (imageW / hScale < photoWidth) {
              w=(int)(imageW / hScale);
            }
          }
        }
        if (currentPhotoObject != null && "s".equals(currentPhotoObject.type)) {
          currentPhotoObject=null;
        }
        if (currentPhotoObject != null && currentPhotoObject == currentPhotoObjectThumb) {
          if (messageObject.type == 1) {
            currentPhotoObjectThumb=null;
          }
 else {
            currentPhotoObject=null;
          }
        }
        if (needQualityPreview) {
          if (!messageObject.needDrawBluredPreview() && (currentPhotoObject == null || currentPhotoObject == currentPhotoObjectThumb) && (currentPhotoObjectThumb == null || !"m".equals(currentPhotoObjectThumb.type))) {
            photoImage.setNeedsQualityThumb(true);
            photoImage.setShouldGenerateQualityThumb(true);
          }
        }
        if (currentMessagesGroup == null && messageObject.caption != null) {
          mediaBackground=false;
        }
        if ((w == 0 || h == 0) && messageObject.type == 8) {
          for (int a=0; a < messageObject.messageOwner.media.document.attributes.size(); a++) {
            TLRPC.DocumentAttribute attribute=messageObject.messageOwner.media.document.attributes.get(a);
            if (attribute instanceof TLRPC.TL_documentAttributeImageSize || attribute instanceof TLRPC.TL_documentAttributeVideo) {
              float scale=(float)attribute.w / (float)photoWidth;
              w=(int)(attribute.w / scale);
              h=(int)(attribute.h / scale);
              if (h > photoHeight) {
                float scale2=h;
                h=photoHeight;
                scale2/=h;
                w=(int)(w / scale2);
              }
 else               if (h < AndroidUtilities.dp(120)) {
                h=AndroidUtilities.dp(120);
                float hScale=(float)attribute.h / h;
                if (attribute.w / hScale < photoWidth) {
                  w=(int)(attribute.w / hScale);
                }
              }
              break;
            }
          }
        }
        if (w == 0 || h == 0) {
          w=h=AndroidUtilities.dp(150);
        }
        if (messageObject.type == 3) {
          if (w < infoWidth + AndroidUtilities.dp(16 + 24)) {
            w=infoWidth + AndroidUtilities.dp(16 + 24);
          }
        }
        if (currentMessagesGroup != null) {
          int firstLineWidth=0;
          int dWidth=getGroupPhotosWidth();
          for (int a=0; a < currentMessagesGroup.posArray.size(); a++) {
            MessageObject.GroupedMessagePosition position=currentMessagesGroup.posArray.get(a);
            if (position.minY == 0) {
              firstLineWidth+=Math.ceil((position.pw + position.leftSpanOffset) / 1000.0f * dWidth);
            }
 else {
              break;
            }
          }
          availableTimeWidth=firstLineWidth - AndroidUtilities.dp(35);
        }
 else {
          availableTimeWidth=maxPhotoWidth - AndroidUtilities.dp(14);
        }
        if (messageObject.type == MessageObject.TYPE_ROUND_VIDEO) {
          availableTimeWidth-=Math.ceil(Theme.chat_audioTimePaint.measureText("00:00")) + AndroidUtilities.dp(26);
        }
        measureTime(messageObject);
        int timeWidthTotal=timeWidth + AndroidUtilities.dp(14 + (messageObject.isOutOwner() ? 20 : 0));
        if (w < timeWidthTotal) {
          w=timeWidthTotal;
        }
        if (messageObject.isRoundVideo()) {
          w=h=Math.min(w,h);
          drawBackground=false;
          photoImage.setRoundRadius(w / 2);
        }
 else         if (messageObject.needDrawBluredPreview()) {
          if (AndroidUtilities.isTablet()) {
            w=h=(int)(AndroidUtilities.getMinTabletSide() * 0.5f);
          }
 else {
            w=h=(int)(Math.min(AndroidUtilities.displaySize.x,AndroidUtilities.displaySize.y) * 0.5f);
          }
        }
        int widthForCaption=0;
        boolean fixPhotoWidth=false;
        if (currentMessagesGroup != null) {
          float maxHeight=Math.max(AndroidUtilities.displaySize.x,AndroidUtilities.displaySize.y) * 0.5f;
          int dWidth=getGroupPhotosWidth();
          w=(int)Math.ceil(currentPosition.pw / 1000.0f * dWidth);
          if (currentPosition.minY != 0 && (messageObject.isOutOwner() && (currentPosition.flags & MessageObject.POSITION_FLAG_LEFT) != 0 || !messageObject.isOutOwner() && (currentPosition.flags & MessageObject.POSITION_FLAG_RIGHT) != 0)) {
            int firstLineWidth=0;
            int currentLineWidth=0;
            for (int a=0; a < currentMessagesGroup.posArray.size(); a++) {
              MessageObject.GroupedMessagePosition position=currentMessagesGroup.posArray.get(a);
              if (position.minY == 0) {
                firstLineWidth+=Math.ceil(position.pw / 1000.0f * dWidth) + (position.leftSpanOffset != 0 ? Math.ceil(position.leftSpanOffset / 1000.0f * dWidth) : 0);
              }
 else               if (position.minY == currentPosition.minY) {
                currentLineWidth+=Math.ceil((position.pw) / 1000.0f * dWidth) + (position.leftSpanOffset != 0 ? Math.ceil(position.leftSpanOffset / 1000.0f * dWidth) : 0);
              }
 else               if (position.minY > currentPosition.minY) {
                break;
              }
            }
            w+=firstLineWidth - currentLineWidth;
          }
          w-=AndroidUtilities.dp(9);
          if (isAvatarVisible) {
            w-=AndroidUtilities.dp(48);
          }
          if (currentPosition.siblingHeights != null) {
            h=0;
            for (int a=0; a < currentPosition.siblingHeights.length; a++) {
              h+=(int)Math.ceil(maxHeight * currentPosition.siblingHeights[a]);
            }
            h+=(currentPosition.maxY - currentPosition.minY) * Math.round(7 * AndroidUtilities.density);
          }
 else {
            h=(int)Math.ceil(maxHeight * currentPosition.ph);
          }
          backgroundWidth=w;
          if ((currentPosition.flags & MessageObject.POSITION_FLAG_RIGHT) != 0 && (currentPosition.flags & MessageObject.POSITION_FLAG_LEFT) != 0) {
            w-=AndroidUtilities.dp(8);
          }
 else           if ((currentPosition.flags & MessageObject.POSITION_FLAG_RIGHT) == 0 && (currentPosition.flags & MessageObject.POSITION_FLAG_LEFT) == 0) {
            w-=AndroidUtilities.dp(11);
          }
 else           if ((currentPosition.flags & MessageObject.POSITION_FLAG_RIGHT) != 0) {
            w-=AndroidUtilities.dp(10);
          }
 else {
            w-=AndroidUtilities.dp(9);
          }
          photoWidth=w;
          if (!currentPosition.edge) {
            photoWidth+=AndroidUtilities.dp(10);
          }
          photoHeight=h;
          widthForCaption+=photoWidth - AndroidUtilities.dp(10);
          if ((currentPosition.flags & MessageObject.POSITION_FLAG_BOTTOM) != 0 || currentMessagesGroup.hasSibling && (currentPosition.flags & MessageObject.POSITION_FLAG_TOP) == 0) {
            widthForCaption+=getAdditionalWidthForPosition(currentPosition);
            int count=currentMessagesGroup.messages.size();
            for (int i=0; i < count; i++) {
              MessageObject m=currentMessagesGroup.messages.get(i);
              MessageObject.GroupedMessagePosition rowPosition=currentMessagesGroup.posArray.get(i);
              if (rowPosition != currentPosition && (rowPosition.flags & MessageObject.POSITION_FLAG_BOTTOM) != 0) {
                w=(int)Math.ceil(rowPosition.pw / 1000.0f * dWidth);
                if (rowPosition.minY != 0 && (messageObject.isOutOwner() && (rowPosition.flags & MessageObject.POSITION_FLAG_LEFT) != 0 || !messageObject.isOutOwner() && (rowPosition.flags & MessageObject.POSITION_FLAG_RIGHT) != 0)) {
                  int firstLineWidth=0;
                  int currentLineWidth=0;
                  for (int a=0; a < currentMessagesGroup.posArray.size(); a++) {
                    MessageObject.GroupedMessagePosition position=currentMessagesGroup.posArray.get(a);
                    if (position.minY == 0) {
                      firstLineWidth+=Math.ceil(position.pw / 1000.0f * dWidth) + (position.leftSpanOffset != 0 ? Math.ceil(position.leftSpanOffset / 1000.0f * dWidth) : 0);
                    }
 else                     if (position.minY == rowPosition.minY) {
                      currentLineWidth+=Math.ceil((position.pw) / 1000.0f * dWidth) + (position.leftSpanOffset != 0 ? Math.ceil(position.leftSpanOffset / 1000.0f * dWidth) : 0);
                    }
 else                     if (position.minY > rowPosition.minY) {
                      break;
                    }
                  }
                  w+=firstLineWidth - currentLineWidth;
                }
                w-=AndroidUtilities.dp(9);
                if ((rowPosition.flags & MessageObject.POSITION_FLAG_RIGHT) != 0 && (rowPosition.flags & MessageObject.POSITION_FLAG_LEFT) != 0) {
                  w-=AndroidUtilities.dp(8);
                }
 else                 if ((rowPosition.flags & MessageObject.POSITION_FLAG_RIGHT) == 0 && (rowPosition.flags & MessageObject.POSITION_FLAG_LEFT) == 0) {
                  w-=AndroidUtilities.dp(11);
                }
 else                 if ((rowPosition.flags & MessageObject.POSITION_FLAG_RIGHT) != 0) {
                  w-=AndroidUtilities.dp(10);
                }
 else {
                  w-=AndroidUtilities.dp(9);
                }
                if (isChat && !m.isOutOwner() && m.needDrawAvatar() && (rowPosition == null || rowPosition.edge)) {
                  w-=AndroidUtilities.dp(48);
                }
                w+=getAdditionalWidthForPosition(rowPosition);
                if (!rowPosition.edge) {
                  w+=AndroidUtilities.dp(10);
                }
                widthForCaption+=w;
                if (rowPosition.minX < currentPosition.minX || currentMessagesGroup.hasSibling && rowPosition.minY != rowPosition.maxY) {
                  captionOffsetX-=w;
                }
              }
              if (m.caption != null) {
                if (currentCaption != null) {
                  currentCaption=null;
                  break;
                }
 else {
                  currentCaption=m.caption;
                }
              }
            }
          }
        }
 else {
          photoHeight=h;
          photoWidth=w;
          currentCaption=messageObject.caption;
          int minCaptionWidth;
          if (AndroidUtilities.isTablet()) {
            minCaptionWidth=(int)(AndroidUtilities.getMinTabletSide() * 0.65f);
          }
 else {
            minCaptionWidth=(int)(Math.min(AndroidUtilities.displaySize.x,AndroidUtilities.displaySize.y) * 0.65f);
          }
          if (!messageObject.needDrawBluredPreview() && currentCaption != null && photoWidth < minCaptionWidth) {
            widthForCaption=minCaptionWidth;
            fixPhotoWidth=true;
          }
 else {
            widthForCaption=photoWidth - AndroidUtilities.dp(10);
          }
          backgroundWidth=photoWidth + AndroidUtilities.dp(8);
          if (!mediaBackground) {
            backgroundWidth+=AndroidUtilities.dp(9);
          }
        }
        if (currentCaption != null) {
          try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
              captionLayout=StaticLayout.Builder.obtain(currentCaption,0,currentCaption.length(),Theme.chat_msgTextPaint,widthForCaption).setBreakStrategy(StaticLayout.BREAK_STRATEGY_HIGH_QUALITY).setHyphenationFrequency(StaticLayout.HYPHENATION_FREQUENCY_NONE).setAlignment(Layout.Alignment.ALIGN_NORMAL).build();
            }
 else {
              captionLayout=new StaticLayout(currentCaption,Theme.chat_msgTextPaint,widthForCaption,Layout.Alignment.ALIGN_NORMAL,1.0f,0.0f,false);
            }
            int lineCount=captionLayout.getLineCount();
            if (lineCount > 0) {
              if (fixPhotoWidth) {
                captionWidth=0;
                for (int a=0; a < lineCount; a++) {
                  captionWidth=(int)Math.max(captionWidth,Math.ceil(captionLayout.getLineWidth(a)));
                  if (captionLayout.getLineLeft(a) != 0) {
                    captionWidth=widthForCaption;
                    break;
                  }
                }
                if (captionWidth > widthForCaption) {
                  captionWidth=widthForCaption;
                }
              }
 else {
                captionWidth=widthForCaption;
              }
              captionHeight=captionLayout.getHeight();
              addedCaptionHeight=captionHeight + AndroidUtilities.dp(9);
              if (currentPosition == null || (currentPosition.flags & MessageObject.POSITION_FLAG_BOTTOM) != 0) {
                additionHeight+=addedCaptionHeight;
                int widthToCheck=Math.max(captionWidth,photoWidth - AndroidUtilities.dp(10));
                float lastLineWidth=captionLayout.getLineWidth(captionLayout.getLineCount() - 1) + captionLayout.getLineLeft(captionLayout.getLineCount() - 1);
                if (widthToCheck + AndroidUtilities.dp(2) - lastLineWidth < timeWidthTotal) {
                  additionHeight+=AndroidUtilities.dp(14);
                  addedCaptionHeight+=AndroidUtilities.dp(14);
                  captionNewLine=1;
                }
              }
 else {
                captionLayout=null;
              }
            }
          }
 catch (          Exception e) {
            FileLog.e(e);
          }
        }
        if (fixPhotoWidth && photoWidth < captionWidth + AndroidUtilities.dp(10)) {
          photoWidth=captionWidth + AndroidUtilities.dp(10);
          backgroundWidth=photoWidth + AndroidUtilities.dp(8);
          if (!mediaBackground) {
            backgroundWidth+=AndroidUtilities.dp(9);
          }
        }
        currentPhotoFilter=currentPhotoFilterThumb=String.format(Locale.US,"%d_%d",(int)(w / AndroidUtilities.density),(int)(h / AndroidUtilities.density));
        if (messageObject.photoThumbs != null && messageObject.photoThumbs.size() > 1 || messageObject.type == 3 || messageObject.type == 8 || messageObject.type == MessageObject.TYPE_ROUND_VIDEO) {
          if (messageObject.needDrawBluredPreview()) {
            currentPhotoFilter+="_b2";
            currentPhotoFilterThumb+="_b2";
          }
 else {
            currentPhotoFilterThumb+="_b";
          }
        }
        boolean noSize=false;
        if (messageObject.type == 3 || messageObject.type == 8 || messageObject.type == MessageObject.TYPE_ROUND_VIDEO) {
          noSize=true;
        }
        if (currentPhotoObject != null && !noSize && currentPhotoObject.size == 0) {
          currentPhotoObject.size=-1;
        }
        if (currentPhotoObjectThumb != null && !noSize && currentPhotoObjectThumb.size == 0) {
          currentPhotoObjectThumb.size=-1;
        }
        if (SharedConfig.autoplayVideo && messageObject.type == 3 && !messageObject.needDrawBluredPreview() && (currentMessageObject.mediaExists || messageObject.canStreamVideo() && DownloadController.getInstance(currentAccount).canDownloadMedia(currentMessageObject))) {
          if (currentPosition != null) {
            autoPlayingMedia=(currentPosition.flags & MessageObject.POSITION_FLAG_LEFT) != 0 && (currentPosition.flags & MessageObject.POSITION_FLAG_RIGHT) != 0;
          }
 else {
            autoPlayingMedia=true;
          }
        }
        if (autoPlayingMedia) {
          photoImage.setAllowStartAnimation(true);
          photoImage.startAnimation();
          TLRPC.Document document=messageObject.messageOwner.media.document;
          photoImage.setImage(ImageLocation.getForDocument(document),ImageLoader.AUTOPLAY_FILTER,ImageLocation.getForObject(currentPhotoObject,photoParentObject),currentPhotoFilter,ImageLocation.getForDocument(currentPhotoObjectThumb,document),currentPhotoFilterThumb,null,messageObject.messageOwner.media.document.size,null,messageObject,0);
        }
 else         if (messageObject.type == 1) {
          if (messageObject.useCustomPhoto) {
            photoImage.setImageBitmap(getResources().getDrawable(R.drawable.theme_preview_image));
          }
 else {
            if (currentPhotoObject != null) {
              boolean photoExist=true;
              String fileName=FileLoader.getAttachFileName(currentPhotoObject);
              if (messageObject.mediaExists) {
                DownloadController.getInstance(currentAccount).removeLoadingFileObserver(this);
              }
 else {
                photoExist=false;
              }
              if (photoExist || DownloadController.getInstance(currentAccount).canDownloadMedia(currentMessageObject) || FileLoader.getInstance(currentAccount).isLoadingFile(fileName)) {
                photoImage.setImage(ImageLocation.getForObject(currentPhotoObject,photoParentObject),currentPhotoFilter,ImageLocation.getForObject(currentPhotoObjectThumb,photoParentObject),currentPhotoFilterThumb,noSize ? 0 : currentPhotoObject.size,null,currentMessageObject,currentMessageObject.shouldEncryptPhotoOrVideo() ? 2 : 0);
              }
 else {
                photoNotSet=true;
                if (currentPhotoObjectThumb != null) {
                  photoImage.setImage(null,null,ImageLocation.getForObject(currentPhotoObjectThumb,photoParentObject),currentPhotoFilterThumb,0,null,currentMessageObject,currentMessageObject.shouldEncryptPhotoOrVideo() ? 2 : 0);
                }
 else {
                  photoImage.setImageBitmap((Drawable)null);
                }
              }
            }
 else {
              photoImage.setImageBitmap((Drawable)null);
            }
          }
        }
 else         if (messageObject.type == 8 || messageObject.type == MessageObject.TYPE_ROUND_VIDEO) {
          String fileName=FileLoader.getAttachFileName(messageObject.messageOwner.media.document);
          int localFile=0;
          if (messageObject.attachPathExists) {
            DownloadController.getInstance(currentAccount).removeLoadingFileObserver(this);
            localFile=1;
          }
 else           if (messageObject.mediaExists) {
            localFile=2;
          }
          boolean autoDownload=false;
          if (MessageObject.isGifDocument(messageObject.messageOwner.media.document) || messageObject.type == MessageObject.TYPE_ROUND_VIDEO) {
            autoDownload=DownloadController.getInstance(currentAccount).canDownloadMedia(currentMessageObject);
          }
          if (!messageObject.isSending() && !messageObject.isEditing() && (localFile != 0 || FileLoader.getInstance(currentAccount).isLoadingFile(fileName) || autoDownload)) {
            if (localFile != 1 && !messageObject.needDrawBluredPreview() && (localFile != 0 || messageObject.canStreamVideo() && autoDownload)) {
              autoPlayingMedia=true;
              photoImage.setImage(ImageLocation.getForDocument(messageObject.messageOwner.media.document),ImageLoader.AUTOPLAY_FILTER,ImageLocation.getForObject(currentPhotoObject,photoParentObject),currentPhotoFilter,ImageLocation.getForObject(currentPhotoObjectThumb,photoParentObject),currentPhotoFilterThumb,null,messageObject.messageOwner.media.document.size,null,messageObject,0);
            }
 else             if (localFile == 1) {
              photoImage.setImage(ImageLocation.getForPath(messageObject.isSendError() ? null : messageObject.messageOwner.attachPath),null,ImageLocation.getForObject(currentPhotoObjectThumb,photoParentObject),currentPhotoFilterThumb,0,null,messageObject,0);
            }
 else {
              photoImage.setImage(ImageLocation.getForDocument(messageObject.messageOwner.media.document),null,ImageLocation.getForObject(currentPhotoObject,photoParentObject),currentPhotoFilter,ImageLocation.getForObject(currentPhotoObjectThumb,photoParentObject),currentPhotoFilterThumb,null,messageObject.messageOwner.media.document.size,null,messageObject,0);
            }
          }
 else {
            photoImage.setImage(ImageLocation.getForObject(currentPhotoObject,photoParentObject),currentPhotoFilter,ImageLocation.getForObject(currentPhotoObjectThumb,photoParentObject),currentPhotoFilterThumb,0,null,messageObject,0);
          }
        }
 else {
          photoImage.setImage(ImageLocation.getForObject(currentPhotoObject,photoParentObject),currentPhotoFilter,ImageLocation.getForObject(currentPhotoObjectThumb,photoParentObject),currentPhotoFilterThumb,0,null,messageObject,currentMessageObject.shouldEncryptPhotoOrVideo() ? 2 : 0);
        }
      }
      setMessageObjectInternal(messageObject);
      if (drawForwardedName && messageObject.needDrawForwarded() && (currentPosition == null || currentPosition.minY == 0)) {
        if (messageObject.type != 5) {
          namesOffset+=AndroidUtilities.dp(5);
        }
      }
 else       if (drawNameLayout && messageObject.messageOwner.reply_to_msg_id == 0) {
        namesOffset+=AndroidUtilities.dp(7);
      }
      totalHeight=photoHeight + AndroidUtilities.dp(14) + namesOffset + additionHeight;
      if (currentPosition != null && (currentPosition.flags & MessageObject.POSITION_FLAG_BOTTOM) == 0) {
        totalHeight-=AndroidUtilities.dp(3);
      }
      int additionalTop=0;
      if (currentPosition != null) {
        photoWidth+=getAdditionalWidthForPosition(currentPosition);
        if ((currentPosition.flags & MessageObject.POSITION_FLAG_TOP) == 0) {
          photoHeight+=AndroidUtilities.dp(4);
          additionalTop-=AndroidUtilities.dp(4);
        }
        if ((currentPosition.flags & MessageObject.POSITION_FLAG_BOTTOM) == 0) {
          photoHeight+=AndroidUtilities.dp(1);
        }
      }
      if (drawPinnedTop) {
        namesOffset-=AndroidUtilities.dp(1);
      }
      int y;
      if (currentPosition != null) {
        if (namesOffset > 0) {
          y=AndroidUtilities.dp(7);
          totalHeight-=AndroidUtilities.dp(2);
        }
 else {
          y=AndroidUtilities.dp(5);
          totalHeight-=AndroidUtilities.dp(4);
        }
      }
 else {
        if (namesOffset > 0) {
          y=AndroidUtilities.dp(7);
          totalHeight-=AndroidUtilities.dp(2);
        }
 else {
          y=AndroidUtilities.dp(5);
          totalHeight-=AndroidUtilities.dp(4);
        }
      }
      photoImage.setImageCoords(0,y + namesOffset + additionalTop,photoWidth,photoHeight);
      invalidate();
    }
    if (currentPosition == null && !messageObject.isAnyKindOfSticker() && addedCaptionHeight == 0) {
      if (captionLayout == null && messageObject.caption != null) {
        try {
          currentCaption=messageObject.caption;
          int width=backgroundWidth - AndroidUtilities.dp(31);
          int widthForCaption=width - AndroidUtilities.dp(10);
          if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            captionLayout=StaticLayout.Builder.obtain(messageObject.caption,0,messageObject.caption.length(),Theme.chat_msgTextPaint,widthForCaption).setBreakStrategy(StaticLayout.BREAK_STRATEGY_HIGH_QUALITY).setHyphenationFrequency(StaticLayout.HYPHENATION_FREQUENCY_NONE).setAlignment(Layout.Alignment.ALIGN_NORMAL).build();
          }
 else {
            captionLayout=new StaticLayout(messageObject.caption,Theme.chat_msgTextPaint,widthForCaption,Layout.Alignment.ALIGN_NORMAL,1.0f,0.0f,false);
          }
        }
 catch (        Exception e) {
          FileLog.e(e);
        }
      }
      if (captionLayout != null) {
        try {
          int width=backgroundWidth - AndroidUtilities.dp(31);
          int widthForCaption=width - AndroidUtilities.dp(10);
          if (captionLayout != null && captionLayout.getLineCount() > 0) {
            captionWidth=widthForCaption;
            int timeWidthTotal=timeWidth + (messageObject.isOutOwner() ? AndroidUtilities.dp(20) : 0);
            captionHeight=captionLayout.getHeight();
            totalHeight+=captionHeight + AndroidUtilities.dp(9);
            float lastLineWidth=captionLayout.getLineWidth(captionLayout.getLineCount() - 1) + captionLayout.getLineLeft(captionLayout.getLineCount() - 1);
            if (width - AndroidUtilities.dp(8) - lastLineWidth < timeWidthTotal) {
              totalHeight+=AndroidUtilities.dp(14);
              captionHeight+=AndroidUtilities.dp(14);
              captionNewLine=2;
            }
          }
        }
 catch (        Exception e) {
          FileLog.e(e);
        }
      }
    }
    if (captionLayout == null && widthBeforeNewTimeLine != -1 && availableTimeWidth - widthBeforeNewTimeLine < timeWidth) {
      totalHeight+=AndroidUtilities.dp(14);
    }
    if (currentMessageObject.eventId != 0 && !currentMessageObject.isMediaEmpty() && currentMessageObject.messageOwner.media.webpage != null) {
      int linkPreviewMaxWidth=backgroundWidth - AndroidUtilities.dp(41);
      hasOldCaptionPreview=true;
      linkPreviewHeight=0;
      TLRPC.WebPage webPage=currentMessageObject.messageOwner.media.webpage;
      try {
        int width=siteNameWidth=(int)Math.ceil(Theme.chat_replyNamePaint.measureText(webPage.site_name) + 1);
        siteNameLayout=new StaticLayout(webPage.site_name,Theme.chat_replyNamePaint,Math.min(width,linkPreviewMaxWidth),Layout.Alignment.ALIGN_NORMAL,1.0f,0.0f,false);
        siteNameRtl=siteNameLayout.getLineLeft(0) != 0;
        int height=siteNameLayout.getLineBottom(siteNameLayout.getLineCount() - 1);
        linkPreviewHeight+=height;
        totalHeight+=height;
      }
 catch (      Exception e) {
        FileLog.e(e);
      }
      try {
        descriptionX=0;
        if (linkPreviewHeight != 0) {
          totalHeight+=AndroidUtilities.dp(2);
        }
        descriptionLayout=StaticLayoutEx.createStaticLayout(webPage.description,Theme.chat_replyTextPaint,linkPreviewMaxWidth,Layout.Alignment.ALIGN_NORMAL,1.0f,AndroidUtilities.dp(1),false,TextUtils.TruncateAt.END,linkPreviewMaxWidth,6);
        int height=descriptionLayout.getLineBottom(descriptionLayout.getLineCount() - 1);
        linkPreviewHeight+=height;
        totalHeight+=height;
        for (int a=0; a < descriptionLayout.getLineCount(); a++) {
          int lineLeft=(int)Math.ceil(descriptionLayout.getLineLeft(a));
          if (lineLeft != 0) {
            if (descriptionX == 0) {
              descriptionX=-lineLeft;
            }
 else {
              descriptionX=Math.max(descriptionX,-lineLeft);
            }
          }
        }
      }
 catch (      Exception e) {
        FileLog.e(e);
      }
      totalHeight+=AndroidUtilities.dp(17);
      if (captionNewLine != 0) {
        totalHeight-=AndroidUtilities.dp(14);
        if (captionNewLine == 2) {
          captionHeight-=AndroidUtilities.dp(14);
        }
      }
    }
    botButtons.clear();
    if (messageIdChanged) {
      botButtonsByData.clear();
      botButtonsByPosition.clear();
      botButtonsLayout=null;
    }
    if (currentPosition == null && messageObject.messageOwner.reply_markup instanceof TLRPC.TL_replyInlineMarkup) {
      int rows=messageObject.messageOwner.reply_markup.rows.size();
      substractBackgroundHeight=keyboardHeight=AndroidUtilities.dp(44 + 4) * rows + AndroidUtilities.dp(1);
      widthForButtons=backgroundWidth - AndroidUtilities.dp(mediaBackground ? 0 : 9);
      boolean fullWidth=false;
      if (messageObject.wantedBotKeyboardWidth > widthForButtons) {
        int maxButtonWidth=-AndroidUtilities.dp(isChat && messageObject.needDrawAvatar() && !messageObject.isOutOwner() ? 62 : 10);
        if (AndroidUtilities.isTablet()) {
          maxButtonWidth+=AndroidUtilities.getMinTabletSide();
        }
 else {
          maxButtonWidth+=Math.min(AndroidUtilities.displaySize.x,AndroidUtilities.displaySize.y) - AndroidUtilities.dp(5);
        }
        widthForButtons=Math.max(backgroundWidth,Math.min(messageObject.wantedBotKeyboardWidth,maxButtonWidth));
      }
      int maxButtonsWidth=0;
      HashMap<String,BotButton> oldByData=new HashMap<>(botButtonsByData);
      HashMap<String,BotButton> oldByPosition;
      if (messageObject.botButtonsLayout != null && botButtonsLayout != null && botButtonsLayout.equals(messageObject.botButtonsLayout.toString())) {
        oldByPosition=new HashMap<>(botButtonsByPosition);
      }
 else {
        if (messageObject.botButtonsLayout != null) {
          botButtonsLayout=messageObject.botButtonsLayout.toString();
        }
        oldByPosition=null;
      }
      botButtonsByData.clear();
      for (int a=0; a < rows; a++) {
        TLRPC.TL_keyboardButtonRow row=messageObject.messageOwner.reply_markup.rows.get(a);
        int buttonsCount=row.buttons.size();
        if (buttonsCount == 0) {
          continue;
        }
        int buttonWidth=(widthForButtons - AndroidUtilities.dp(5) * (buttonsCount - 1) - AndroidUtilities.dp(2)) / buttonsCount;
        for (int b=0; b < row.buttons.size(); b++) {
          BotButton botButton=new BotButton();
          botButton.button=row.buttons.get(b);
          String key=Utilities.bytesToHex(botButton.button.data);
          String position=a + "" + b;
          BotButton oldButton;
          if (oldByPosition != null) {
            oldButton=oldByPosition.get(position);
          }
 else {
            oldButton=oldByData.get(key);
          }
          if (oldButton != null) {
            botButton.progressAlpha=oldButton.progressAlpha;
            botButton.angle=oldButton.angle;
            botButton.lastUpdateTime=oldButton.lastUpdateTime;
          }
 else {
            botButton.lastUpdateTime=System.currentTimeMillis();
          }
          botButtonsByData.put(key,botButton);
          botButtonsByPosition.put(position,botButton);
          botButton.x=b * (buttonWidth + AndroidUtilities.dp(5));
          botButton.y=a * AndroidUtilities.dp(44 + 4) + AndroidUtilities.dp(5);
          botButton.width=buttonWidth;
          botButton.height=AndroidUtilities.dp(44);
          CharSequence buttonText;
          if (botButton.button instanceof TLRPC.TL_keyboardButtonBuy && (messageObject.messageOwner.media.flags & 4) != 0) {
            buttonText=LocaleController.getString("PaymentReceipt",R.string.PaymentReceipt);
          }
 else {
            buttonText=Emoji.replaceEmoji(botButton.button.text,Theme.chat_botButtonPaint.getFontMetricsInt(),AndroidUtilities.dp(15),false);
            buttonText=TextUtils.ellipsize(buttonText,Theme.chat_botButtonPaint,buttonWidth - AndroidUtilities.dp(10),TextUtils.TruncateAt.END);
          }
          botButton.title=new StaticLayout(buttonText,Theme.chat_botButtonPaint,buttonWidth - AndroidUtilities.dp(10),Layout.Alignment.ALIGN_CENTER,1.0f,0.0f,false);
          botButtons.add(botButton);
          if (b == row.buttons.size() - 1) {
            maxButtonsWidth=Math.max(maxButtonsWidth,botButton.x + botButton.width);
          }
        }
      }
      widthForButtons=maxButtonsWidth;
    }
 else {
      substractBackgroundHeight=0;
      keyboardHeight=0;
    }
    if (drawPinnedBottom && drawPinnedTop) {
      totalHeight-=AndroidUtilities.dp(2);
    }
 else     if (drawPinnedBottom) {
      totalHeight-=AndroidUtilities.dp(1);
    }
 else     if (drawPinnedTop && pinnedBottom && currentPosition != null && currentPosition.siblingHeights == null) {
      totalHeight-=AndroidUtilities.dp(1);
    }
    if (messageObject.isAnyKindOfSticker() && totalHeight < AndroidUtilities.dp(70)) {
      totalHeight=AndroidUtilities.dp(70);
    }
    if (!drawPhotoImage) {
      photoImage.setImageBitmap((Drawable)null);
    }
    if (documentAttachType == DOCUMENT_ATTACH_TYPE_MUSIC) {
      if (MessageObject.isDocumentHasThumb(documentAttach)) {
        TLRPC.PhotoSize thumb=FileLoader.getClosestPhotoSizeWithSize(documentAttach.thumbs,90);
        radialProgress.setImageOverlay(thumb,documentAttach,messageObject);
      }
 else {
        String artworkUrl=messageObject.getArtworkUrl(true);
        if (!TextUtils.isEmpty(artworkUrl)) {
          radialProgress.setImageOverlay(artworkUrl);
        }
 else {
          radialProgress.setImageOverlay(null,null,null);
        }
      }
    }
 else {
      radialProgress.setImageOverlay(null,null,null);
    }
  }
  updateWaveform();
  updateButtonState(false,dataChanged && !messageObject.cancelEditing,true);
  if (buttonState == 2 && documentAttachType == DOCUMENT_ATTACH_TYPE_AUDIO && DownloadController.getInstance(currentAccount).canDownloadMedia(messageObject)) {
    FileLoader.getInstance(currentAccount).loadFile(documentAttach,currentMessageObject,1,0);
    buttonState=4;
    radialProgress.setIcon(getIconForCurrentState(),false,false);
  }
  accessibilityVirtualViewBounds.clear();
}
