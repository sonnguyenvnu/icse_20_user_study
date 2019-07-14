private void drawContent(Canvas canvas){
  if (needNewVisiblePart && currentMessageObject.type == 0) {
    getLocalVisibleRect(scrollRect);
    setVisiblePart(scrollRect.top,scrollRect.bottom - scrollRect.top);
    needNewVisiblePart=false;
  }
  forceNotDrawTime=currentMessagesGroup != null;
  photoImage.setVisible(!PhotoViewer.isShowingImage(currentMessageObject) && !SecretMediaViewer.getInstance().isShowingImage(currentMessageObject),false);
  if (!photoImage.getVisible()) {
    mediaWasInvisible=true;
    timeWasInvisible=true;
    if (animatingNoSound == 1) {
      animatingNoSoundProgress=0.0f;
      animatingNoSound=0;
    }
 else     if (animatingNoSound == 2) {
      animatingNoSoundProgress=1.0f;
      animatingNoSound=0;
    }
  }
 else   if (groupPhotoInvisible) {
    timeWasInvisible=true;
  }
 else   if (mediaWasInvisible || timeWasInvisible) {
    if (mediaWasInvisible) {
      controlsAlpha=0.0f;
      mediaWasInvisible=false;
    }
    if (timeWasInvisible) {
      timeAlpha=0.0f;
      timeWasInvisible=false;
    }
    lastControlsAlphaChangeTime=System.currentTimeMillis();
    totalChangeTime=0;
  }
  radialProgress.setProgressColor(Theme.getColor(Theme.key_chat_mediaProgress));
  videoRadialProgress.setProgressColor(Theme.getColor(Theme.key_chat_mediaProgress));
  boolean imageDrawn=false;
  if (currentMessageObject.type == 0) {
    if (currentMessageObject.isOutOwner()) {
      textX=currentBackgroundDrawable.getBounds().left + AndroidUtilities.dp(11);
    }
 else {
      textX=currentBackgroundDrawable.getBounds().left + AndroidUtilities.dp(!mediaBackground && drawPinnedBottom ? 11 : 17);
    }
    if (hasGamePreview) {
      textX+=AndroidUtilities.dp(11);
      textY=AndroidUtilities.dp(14) + namesOffset;
      if (siteNameLayout != null) {
        textY+=siteNameLayout.getLineBottom(siteNameLayout.getLineCount() - 1);
      }
    }
 else     if (hasInvoicePreview) {
      textY=AndroidUtilities.dp(14) + namesOffset;
      if (siteNameLayout != null) {
        textY+=siteNameLayout.getLineBottom(siteNameLayout.getLineCount() - 1);
      }
    }
 else {
      textY=AndroidUtilities.dp(10) + namesOffset;
    }
    unmovedTextX=textX;
    if (currentMessageObject.textXOffset != 0 && replyNameLayout != null) {
      int diff=backgroundWidth - AndroidUtilities.dp(31) - currentMessageObject.textWidth;
      if (!hasNewLineForTime) {
        diff-=timeWidth + AndroidUtilities.dp(4 + (currentMessageObject.isOutOwner() ? 20 : 0));
      }
      if (diff > 0) {
        textX+=diff;
      }
    }
    if (currentMessageObject.textLayoutBlocks != null && !currentMessageObject.textLayoutBlocks.isEmpty()) {
      if (fullyDraw) {
        firstVisibleBlockNum=0;
        lastVisibleBlockNum=currentMessageObject.textLayoutBlocks.size();
      }
      if (firstVisibleBlockNum >= 0) {
        for (int a=firstVisibleBlockNum; a <= lastVisibleBlockNum; a++) {
          if (a >= currentMessageObject.textLayoutBlocks.size()) {
            break;
          }
          MessageObject.TextLayoutBlock block=currentMessageObject.textLayoutBlocks.get(a);
          canvas.save();
          canvas.translate(textX - (block.isRtl() ? (int)Math.ceil(currentMessageObject.textXOffset) : 0),textY + block.textYOffset);
          if (pressedLink != null && a == linkBlockNum) {
            for (int b=0; b < urlPath.size(); b++) {
              canvas.drawPath(urlPath.get(b),Theme.chat_urlPaint);
            }
          }
          if (a == linkSelectionBlockNum && !urlPathSelection.isEmpty()) {
            for (int b=0; b < urlPathSelection.size(); b++) {
              canvas.drawPath(urlPathSelection.get(b),Theme.chat_textSearchSelectionPaint);
            }
          }
          try {
            block.textLayout.draw(canvas);
          }
 catch (          Exception e) {
            FileLog.e(e);
          }
          canvas.restore();
        }
      }
    }
    if (hasLinkPreview || hasGamePreview || hasInvoicePreview) {
      int startY;
      int linkX;
      if (hasGamePreview) {
        startY=AndroidUtilities.dp(14) + namesOffset;
        linkX=unmovedTextX - AndroidUtilities.dp(10);
      }
 else       if (hasInvoicePreview) {
        startY=AndroidUtilities.dp(14) + namesOffset;
        linkX=unmovedTextX + AndroidUtilities.dp(1);
      }
 else {
        startY=textY + currentMessageObject.textHeight + AndroidUtilities.dp(8);
        linkX=unmovedTextX + AndroidUtilities.dp(1);
      }
      int linkPreviewY=startY;
      int smallImageStartY=0;
      if (!hasInvoicePreview) {
        Theme.chat_replyLinePaint.setColor(Theme.getColor(currentMessageObject.isOutOwner() ? Theme.key_chat_outPreviewLine : Theme.key_chat_inPreviewLine));
        canvas.drawRect(linkX,linkPreviewY - AndroidUtilities.dp(3),linkX + AndroidUtilities.dp(2),linkPreviewY + linkPreviewHeight + AndroidUtilities.dp(3),Theme.chat_replyLinePaint);
      }
      if (siteNameLayout != null) {
        Theme.chat_replyNamePaint.setColor(Theme.getColor(currentMessageObject.isOutOwner() ? Theme.key_chat_outSiteNameText : Theme.key_chat_inSiteNameText));
        canvas.save();
        int x;
        if (siteNameRtl) {
          x=backgroundWidth - siteNameWidth - AndroidUtilities.dp(32);
        }
 else {
          x=(hasInvoicePreview ? 0 : AndroidUtilities.dp(10));
        }
        canvas.translate(linkX + x,linkPreviewY - AndroidUtilities.dp(3));
        siteNameLayout.draw(canvas);
        canvas.restore();
        linkPreviewY+=siteNameLayout.getLineBottom(siteNameLayout.getLineCount() - 1);
      }
      if ((hasGamePreview || hasInvoicePreview) && currentMessageObject.textHeight != 0) {
        startY+=currentMessageObject.textHeight + AndroidUtilities.dp(4);
        linkPreviewY+=currentMessageObject.textHeight + AndroidUtilities.dp(4);
      }
      if (drawPhotoImage && drawInstantView || drawInstantViewType == 6 && imageBackgroundColor != 0) {
        if (linkPreviewY != startY) {
          linkPreviewY+=AndroidUtilities.dp(2);
        }
        if (imageBackgroundSideColor != 0) {
          int x=linkX + AndroidUtilities.dp(10);
          photoImage.setImageCoords(x + (imageBackgroundSideWidth - photoImage.getImageWidth()) / 2,linkPreviewY,photoImage.getImageWidth(),photoImage.getImageHeight());
          rect.set(x,photoImage.getImageY(),x + imageBackgroundSideWidth,photoImage.getImageY2());
          Theme.chat_instantViewPaint.setColor(imageBackgroundSideColor);
          canvas.drawRoundRect(rect,AndroidUtilities.dp(4),AndroidUtilities.dp(4),Theme.chat_instantViewPaint);
        }
 else {
          photoImage.setImageCoords(linkX + AndroidUtilities.dp(10),linkPreviewY,photoImage.getImageWidth(),photoImage.getImageHeight());
        }
        if (imageBackgroundColor != 0) {
          Theme.chat_instantViewPaint.setColor(imageBackgroundColor);
          rect.set(photoImage.getImageX(),photoImage.getImageY(),photoImage.getImageX2(),photoImage.getImageY2());
          if (imageBackgroundSideColor != 0) {
            canvas.drawRect(photoImage.getImageX(),photoImage.getImageY(),photoImage.getImageX2(),photoImage.getImageY2(),Theme.chat_instantViewPaint);
          }
 else {
            canvas.drawRoundRect(rect,AndroidUtilities.dp(4),AndroidUtilities.dp(4),Theme.chat_instantViewPaint);
          }
        }
        if (drawPhotoImage && drawInstantView) {
          if (drawImageButton) {
            int size=AndroidUtilities.dp(48);
            buttonX=(int)(photoImage.getImageX() + (photoImage.getImageWidth() - size) / 2.0f);
            buttonY=(int)(photoImage.getImageY() + (photoImage.getImageHeight() - size) / 2.0f);
            radialProgress.setProgressRect(buttonX,buttonY,buttonX + size,buttonY + size);
          }
          imageDrawn=photoImage.draw(canvas);
        }
        linkPreviewY+=photoImage.getImageHeight() + AndroidUtilities.dp(6);
      }
      if (currentMessageObject.isOutOwner()) {
        Theme.chat_replyNamePaint.setColor(Theme.getColor(Theme.key_chat_messageTextOut));
        Theme.chat_replyTextPaint.setColor(Theme.getColor(Theme.key_chat_messageTextOut));
      }
 else {
        Theme.chat_replyNamePaint.setColor(Theme.getColor(Theme.key_chat_messageTextIn));
        Theme.chat_replyTextPaint.setColor(Theme.getColor(Theme.key_chat_messageTextIn));
      }
      if (titleLayout != null) {
        if (linkPreviewY != startY) {
          linkPreviewY+=AndroidUtilities.dp(2);
        }
        smallImageStartY=linkPreviewY - AndroidUtilities.dp(1);
        canvas.save();
        canvas.translate(linkX + AndroidUtilities.dp(10) + titleX,linkPreviewY - AndroidUtilities.dp(3));
        titleLayout.draw(canvas);
        canvas.restore();
        linkPreviewY+=titleLayout.getLineBottom(titleLayout.getLineCount() - 1);
      }
      if (authorLayout != null) {
        if (linkPreviewY != startY) {
          linkPreviewY+=AndroidUtilities.dp(2);
        }
        if (smallImageStartY == 0) {
          smallImageStartY=linkPreviewY - AndroidUtilities.dp(1);
        }
        canvas.save();
        canvas.translate(linkX + AndroidUtilities.dp(10) + authorX,linkPreviewY - AndroidUtilities.dp(3));
        authorLayout.draw(canvas);
        canvas.restore();
        linkPreviewY+=authorLayout.getLineBottom(authorLayout.getLineCount() - 1);
      }
      if (descriptionLayout != null) {
        if (linkPreviewY != startY) {
          linkPreviewY+=AndroidUtilities.dp(2);
        }
        if (smallImageStartY == 0) {
          smallImageStartY=linkPreviewY - AndroidUtilities.dp(1);
        }
        descriptionY=linkPreviewY - AndroidUtilities.dp(3);
        canvas.save();
        canvas.translate(linkX + (hasInvoicePreview ? 0 : AndroidUtilities.dp(10)) + descriptionX,descriptionY);
        if (pressedLink != null && linkBlockNum == -10) {
          for (int b=0; b < urlPath.size(); b++) {
            canvas.drawPath(urlPath.get(b),Theme.chat_urlPaint);
          }
        }
        descriptionLayout.draw(canvas);
        canvas.restore();
        linkPreviewY+=descriptionLayout.getLineBottom(descriptionLayout.getLineCount() - 1);
      }
      if (drawPhotoImage && !drawInstantView) {
        if (linkPreviewY != startY) {
          linkPreviewY+=AndroidUtilities.dp(2);
        }
        if (isSmallImage) {
          photoImage.setImageCoords(linkX + backgroundWidth - AndroidUtilities.dp(81),smallImageStartY,photoImage.getImageWidth(),photoImage.getImageHeight());
        }
 else {
          photoImage.setImageCoords(linkX + (hasInvoicePreview ? -AndroidUtilities.dp(6.3f) : AndroidUtilities.dp(10)),linkPreviewY,photoImage.getImageWidth(),photoImage.getImageHeight());
          if (drawImageButton) {
            int size=AndroidUtilities.dp(48);
            buttonX=(int)(photoImage.getImageX() + (photoImage.getImageWidth() - size) / 2.0f);
            buttonY=(int)(photoImage.getImageY() + (photoImage.getImageHeight() - size) / 2.0f);
            radialProgress.setProgressRect(buttonX,buttonY,buttonX + size,buttonY + size);
          }
        }
        if (currentMessageObject.isRoundVideo() && MediaController.getInstance().isPlayingMessage(currentMessageObject) && MediaController.getInstance().isVideoDrawingReady()) {
          imageDrawn=true;
          drawTime=true;
        }
 else {
          imageDrawn=photoImage.draw(canvas);
        }
      }
      if (documentAttachType == DOCUMENT_ATTACH_TYPE_VIDEO) {
        videoButtonX=photoImage.getImageX() + AndroidUtilities.dp(8);
        videoButtonY=photoImage.getImageY() + AndroidUtilities.dp(8);
        videoRadialProgress.setProgressRect(videoButtonX,videoButtonY,videoButtonX + AndroidUtilities.dp(24),videoButtonY + AndroidUtilities.dp(24));
      }
      if (photosCountLayout != null && photoImage.getVisible()) {
        int x=photoImage.getImageX() + photoImage.getImageWidth() - AndroidUtilities.dp(8) - photosCountWidth;
        int y=photoImage.getImageY() + photoImage.getImageHeight() - AndroidUtilities.dp(19);
        rect.set(x - AndroidUtilities.dp(4),y - AndroidUtilities.dp(1.5f),x + photosCountWidth + AndroidUtilities.dp(4),y + AndroidUtilities.dp(14.5f));
        int oldAlpha=Theme.chat_timeBackgroundPaint.getAlpha();
        Theme.chat_timeBackgroundPaint.setAlpha((int)(oldAlpha * controlsAlpha));
        Theme.chat_durationPaint.setAlpha((int)(255 * controlsAlpha));
        canvas.drawRoundRect(rect,AndroidUtilities.dp(4),AndroidUtilities.dp(4),Theme.chat_timeBackgroundPaint);
        Theme.chat_timeBackgroundPaint.setAlpha(oldAlpha);
        canvas.save();
        canvas.translate(x,y);
        photosCountLayout.draw(canvas);
        canvas.restore();
        Theme.chat_durationPaint.setAlpha(255);
      }
      if (videoInfoLayout != null && (!drawPhotoImage || photoImage.getVisible()) && imageBackgroundSideColor == 0) {
        int x;
        int y;
        if (hasGamePreview || hasInvoicePreview || documentAttachType == DOCUMENT_ATTACH_TYPE_WALLPAPER) {
          if (drawPhotoImage) {
            x=photoImage.getImageX() + AndroidUtilities.dp(8.5f);
            y=photoImage.getImageY() + AndroidUtilities.dp(6);
            int height=AndroidUtilities.dp(documentAttachType == DOCUMENT_ATTACH_TYPE_WALLPAPER ? 14.5f : 16.5f);
            rect.set(x - AndroidUtilities.dp(4),y - AndroidUtilities.dp(1.5f),x + durationWidth + AndroidUtilities.dp(4),y + height);
            canvas.drawRoundRect(rect,AndroidUtilities.dp(4),AndroidUtilities.dp(4),Theme.chat_timeBackgroundPaint);
          }
 else {
            x=linkX;
            y=linkPreviewY;
          }
        }
 else {
          x=photoImage.getImageX() + photoImage.getImageWidth() - AndroidUtilities.dp(8) - durationWidth;
          y=photoImage.getImageY() + photoImage.getImageHeight() - AndroidUtilities.dp(19);
          rect.set(x - AndroidUtilities.dp(4),y - AndroidUtilities.dp(1.5f),x + durationWidth + AndroidUtilities.dp(4),y + AndroidUtilities.dp(14.5f));
          canvas.drawRoundRect(rect,AndroidUtilities.dp(4),AndroidUtilities.dp(4),Theme.chat_timeBackgroundPaint);
        }
        canvas.save();
        canvas.translate(x,y);
        if (hasInvoicePreview) {
          if (drawPhotoImage) {
            Theme.chat_shipmentPaint.setColor(Theme.getColor(Theme.key_chat_previewGameText));
          }
 else {
            if (currentMessageObject.isOutOwner()) {
              Theme.chat_shipmentPaint.setColor(Theme.getColor(Theme.key_chat_messageTextOut));
            }
 else {
              Theme.chat_shipmentPaint.setColor(Theme.getColor(Theme.key_chat_messageTextIn));
            }
          }
        }
        videoInfoLayout.draw(canvas);
        canvas.restore();
      }
      if (drawInstantView) {
        Drawable instantDrawable;
        int instantY=startY + linkPreviewHeight + AndroidUtilities.dp(10);
        Paint backPaint=Theme.chat_instantViewRectPaint;
        if (currentMessageObject.isOutOwner()) {
          instantDrawable=Theme.chat_msgOutInstantDrawable;
          Theme.chat_instantViewPaint.setColor(Theme.getColor(Theme.key_chat_outPreviewInstantText));
          backPaint.setColor(Theme.getColor(Theme.key_chat_outPreviewInstantText));
        }
 else {
          instantDrawable=Theme.chat_msgInInstantDrawable;
          Theme.chat_instantViewPaint.setColor(Theme.getColor(Theme.key_chat_inPreviewInstantText));
          backPaint.setColor(Theme.getColor(Theme.key_chat_inPreviewInstantText));
        }
        if (Build.VERSION.SDK_INT >= 21) {
          selectorDrawable.setBounds(linkX,instantY,linkX + instantWidth,instantY + AndroidUtilities.dp(36));
          selectorDrawable.draw(canvas);
        }
        rect.set(linkX,instantY,linkX + instantWidth,instantY + AndroidUtilities.dp(36));
        canvas.drawRoundRect(rect,AndroidUtilities.dp(6),AndroidUtilities.dp(6),backPaint);
        if (drawInstantViewType == 0) {
          setDrawableBounds(instantDrawable,instantTextLeftX + instantTextX + linkX - AndroidUtilities.dp(15),instantY + AndroidUtilities.dp(11.5f),AndroidUtilities.dp(9),AndroidUtilities.dp(13));
          instantDrawable.draw(canvas);
        }
        if (instantViewLayout != null) {
          canvas.save();
          canvas.translate(linkX + instantTextX,instantY + AndroidUtilities.dp(10.5f));
          instantViewLayout.draw(canvas);
          canvas.restore();
        }
      }
    }
    drawTime=true;
  }
 else   if (drawPhotoImage) {
    if (currentMessageObject.isRoundVideo() && MediaController.getInstance().isPlayingMessage(currentMessageObject) && MediaController.getInstance().isVideoDrawingReady()) {
      imageDrawn=true;
      drawTime=true;
    }
 else {
      if (currentMessageObject.type == MessageObject.TYPE_ROUND_VIDEO && Theme.chat_roundVideoShadow != null) {
        int x=photoImage.getImageX() - AndroidUtilities.dp(3);
        int y=photoImage.getImageY() - AndroidUtilities.dp(2);
        Theme.chat_roundVideoShadow.setAlpha(255);
        Theme.chat_roundVideoShadow.setBounds(x,y,x + AndroidUtilities.roundMessageSize + AndroidUtilities.dp(6),y + AndroidUtilities.roundMessageSize + AndroidUtilities.dp(6));
        Theme.chat_roundVideoShadow.draw(canvas);
        if (!photoImage.hasBitmapImage() || photoImage.getCurrentAlpha() != 1) {
          Theme.chat_docBackPaint.setColor(Theme.getColor(currentMessageObject.isOutOwner() ? Theme.key_chat_outBubble : Theme.key_chat_inBubble));
          canvas.drawCircle(photoImage.getCenterX(),photoImage.getCenterY(),photoImage.getImageWidth() / 2,Theme.chat_docBackPaint);
        }
      }
      drawPhotoCheckBox=photoCheckBox != null && (checkBoxVisible || photoCheckBox.getProgress() != 0 || checkBoxAnimationInProgress) && currentMessagesGroup != null && currentMessagesGroup.messages.size() > 1;
      if (drawPhotoCheckBox && (photoCheckBox.isChecked() || photoCheckBox.getProgress() != 0 || checkBoxAnimationInProgress)) {
        Theme.chat_replyLinePaint.setColor(Theme.getColor(currentMessageObject.isOutOwner() ? Theme.key_chat_outBubbleSelected : Theme.key_chat_inBubbleSelected));
        rect.set(photoImage.getImageX(),photoImage.getImageY(),photoImage.getImageX2(),photoImage.getImageY2());
        canvas.drawRoundRect(rect,AndroidUtilities.dp(4),AndroidUtilities.dp(4),Theme.chat_replyLinePaint);
        photoImage.setSideClip(AndroidUtilities.dp(14) * photoCheckBox.getProgress());
        if (checkBoxAnimationInProgress) {
          photoCheckBox.setBackgroundAlpha(checkBoxAnimationProgress);
        }
 else {
          photoCheckBox.setBackgroundAlpha(checkBoxVisible ? 1.0f : photoCheckBox.getProgress());
        }
      }
 else {
        photoImage.setSideClip(0);
      }
      imageDrawn=photoImage.draw(canvas);
      boolean drawTimeOld=drawTime;
      drawTime=photoImage.getVisible();
      if (currentPosition != null && drawTimeOld != drawTime) {
        ViewGroup viewGroup=(ViewGroup)getParent();
        if (viewGroup != null) {
          if (!currentPosition.last) {
            int count=viewGroup.getChildCount();
            for (int a=0; a < count; a++) {
              View child=viewGroup.getChildAt(a);
              if (child == this || !(child instanceof ChatMessageCell)) {
                continue;
              }
              ChatMessageCell cell=(ChatMessageCell)child;
              if (cell.getCurrentMessagesGroup() == currentMessagesGroup) {
                MessageObject.GroupedMessagePosition position=cell.getCurrentPosition();
                if (position.last && position.maxY == currentPosition.maxY && cell.timeX - AndroidUtilities.dp(4) + cell.getLeft() < getRight()) {
                  cell.groupPhotoInvisible=!drawTime;
                  cell.invalidate();
                  viewGroup.invalidate();
                }
              }
            }
          }
 else {
            viewGroup.invalidate();
          }
        }
      }
    }
  }
  if (documentAttachType == DOCUMENT_ATTACH_TYPE_GIF) {
    if (photoImage.getVisible() && !hasGamePreview && !currentMessageObject.needDrawBluredPreview()) {
      int oldAlpha=((BitmapDrawable)Theme.chat_msgMediaMenuDrawable).getPaint().getAlpha();
      Theme.chat_msgMediaMenuDrawable.setAlpha((int)(oldAlpha * controlsAlpha));
      setDrawableBounds(Theme.chat_msgMediaMenuDrawable,otherX=photoImage.getImageX() + photoImage.getImageWidth() - AndroidUtilities.dp(14),otherY=photoImage.getImageY() + AndroidUtilities.dp(8.1f));
      Theme.chat_msgMediaMenuDrawable.draw(canvas);
      Theme.chat_msgMediaMenuDrawable.setAlpha(oldAlpha);
    }
  }
 else   if (documentAttachType == DOCUMENT_ATTACH_TYPE_ROUND) {
    if (durationLayout != null) {
      int x1;
      int y1;
      boolean playing=MediaController.getInstance().isPlayingMessage(currentMessageObject);
      if (playing && currentMessageObject.type == MessageObject.TYPE_ROUND_VIDEO) {
        drawRoundProgress(canvas);
        drawOverlays(canvas);
      }
      if (currentMessageObject.type == MessageObject.TYPE_ROUND_VIDEO) {
        x1=backgroundDrawableLeft + AndroidUtilities.dp(8);
        y1=layoutHeight - AndroidUtilities.dp(28 - (drawPinnedBottom ? 2 : 0));
        rect.set(x1,y1,x1 + timeWidthAudio + AndroidUtilities.dp(8 + 12 + 2),y1 + AndroidUtilities.dp(17));
        int oldAlpha=Theme.chat_actionBackgroundPaint.getAlpha();
        Theme.chat_actionBackgroundPaint.setAlpha((int)(oldAlpha * timeAlpha));
        canvas.drawRoundRect(rect,AndroidUtilities.dp(4),AndroidUtilities.dp(4),Theme.chat_actionBackgroundPaint);
        Theme.chat_actionBackgroundPaint.setAlpha(oldAlpha);
        if (!playing && currentMessageObject.isContentUnread()) {
          Theme.chat_docBackPaint.setColor(Theme.getColor(Theme.key_chat_mediaTimeText));
          Theme.chat_docBackPaint.setAlpha((int)(255 * timeAlpha));
          canvas.drawCircle(x1 + timeWidthAudio + AndroidUtilities.dp(12),y1 + AndroidUtilities.dp(8.3f),AndroidUtilities.dp(3),Theme.chat_docBackPaint);
        }
 else {
          if (playing && !MediaController.getInstance().isMessagePaused()) {
            roundVideoPlayingDrawable.start();
          }
 else {
            roundVideoPlayingDrawable.stop();
          }
          setDrawableBounds(roundVideoPlayingDrawable,x1 + timeWidthAudio + AndroidUtilities.dp(6),y1 + AndroidUtilities.dp(2.3f));
          roundVideoPlayingDrawable.draw(canvas);
        }
        x1+=AndroidUtilities.dp(4);
        y1+=AndroidUtilities.dp(1.7f);
      }
 else {
        x1=backgroundDrawableLeft + AndroidUtilities.dp(currentMessageObject.isOutOwner() || drawPinnedBottom ? 12 : 18);
        y1=layoutHeight - AndroidUtilities.dp(6.3f - (drawPinnedBottom ? 2 : 0)) - timeLayout.getHeight();
      }
      Theme.chat_timePaint.setAlpha((int)(255 * timeAlpha));
      canvas.save();
      canvas.translate(x1,y1);
      durationLayout.draw(canvas);
      canvas.restore();
      Theme.chat_timePaint.setAlpha(255);
    }
  }
 else   if (documentAttachType == DOCUMENT_ATTACH_TYPE_MUSIC) {
    if (currentMessageObject.isOutOwner()) {
      Theme.chat_audioTitlePaint.setColor(Theme.getColor(Theme.key_chat_outAudioTitleText));
      Theme.chat_audioPerformerPaint.setColor(Theme.getColor(isDrawSelectionBackground() ? Theme.key_chat_outAudioPerformerSelectedText : Theme.key_chat_outAudioPerformerText));
      Theme.chat_audioTimePaint.setColor(Theme.getColor(isDrawSelectionBackground() ? Theme.key_chat_outAudioDurationSelectedText : Theme.key_chat_outAudioDurationText));
      radialProgress.setProgressColor(Theme.getColor(isDrawSelectionBackground() || buttonPressed != 0 ? Theme.key_chat_outAudioSelectedProgress : Theme.key_chat_outAudioProgress));
    }
 else {
      Theme.chat_audioTitlePaint.setColor(Theme.getColor(Theme.key_chat_inAudioTitleText));
      Theme.chat_audioPerformerPaint.setColor(Theme.getColor(isDrawSelectionBackground() ? Theme.key_chat_inAudioPerformerSelectedText : Theme.key_chat_inAudioPerformerText));
      Theme.chat_audioTimePaint.setColor(Theme.getColor(isDrawSelectionBackground() ? Theme.key_chat_inAudioDurationSelectedText : Theme.key_chat_inAudioDurationText));
      radialProgress.setProgressColor(Theme.getColor(isDrawSelectionBackground() || buttonPressed != 0 ? Theme.key_chat_inAudioSelectedProgress : Theme.key_chat_inAudioProgress));
    }
    radialProgress.draw(canvas);
    canvas.save();
    canvas.translate(timeAudioX + songX,AndroidUtilities.dp(13) + namesOffset + mediaOffsetY);
    songLayout.draw(canvas);
    canvas.restore();
    canvas.save();
    if (MediaController.getInstance().isPlayingMessage(currentMessageObject)) {
      canvas.translate(seekBarX,seekBarY);
      seekBar.draw(canvas);
    }
 else {
      canvas.translate(timeAudioX + performerX,AndroidUtilities.dp(35) + namesOffset + mediaOffsetY);
      performerLayout.draw(canvas);
    }
    canvas.restore();
    canvas.save();
    canvas.translate(timeAudioX,AndroidUtilities.dp(57) + namesOffset + mediaOffsetY);
    durationLayout.draw(canvas);
    canvas.restore();
    Drawable menuDrawable;
    if (currentMessageObject.isOutOwner()) {
      menuDrawable=isDrawSelectionBackground() ? Theme.chat_msgOutMenuSelectedDrawable : Theme.chat_msgOutMenuDrawable;
    }
 else {
      menuDrawable=isDrawSelectionBackground() ? Theme.chat_msgInMenuSelectedDrawable : Theme.chat_msgInMenuDrawable;
    }
    setDrawableBounds(menuDrawable,otherX=buttonX + backgroundWidth - AndroidUtilities.dp(currentMessageObject.type == 0 ? 58 : 48),otherY=buttonY - AndroidUtilities.dp(5));
    menuDrawable.draw(canvas);
  }
 else   if (documentAttachType == DOCUMENT_ATTACH_TYPE_AUDIO) {
    if (currentMessageObject.isOutOwner()) {
      Theme.chat_audioTimePaint.setColor(Theme.getColor(isDrawSelectionBackground() ? Theme.key_chat_outAudioDurationSelectedText : Theme.key_chat_outAudioDurationText));
      radialProgress.setProgressColor(Theme.getColor(isDrawSelectionBackground() || buttonPressed != 0 ? Theme.key_chat_outAudioSelectedProgress : Theme.key_chat_outAudioProgress));
    }
 else {
      Theme.chat_audioTimePaint.setColor(Theme.getColor(isDrawSelectionBackground() ? Theme.key_chat_inAudioDurationSelectedText : Theme.key_chat_inAudioDurationText));
      radialProgress.setProgressColor(Theme.getColor(isDrawSelectionBackground() || buttonPressed != 0 ? Theme.key_chat_inAudioSelectedProgress : Theme.key_chat_inAudioProgress));
    }
    radialProgress.draw(canvas);
    canvas.save();
    if (useSeekBarWaweform) {
      canvas.translate(seekBarX + AndroidUtilities.dp(13),seekBarY);
      seekBarWaveform.draw(canvas);
    }
 else {
      canvas.translate(seekBarX,seekBarY);
      seekBar.draw(canvas);
    }
    canvas.restore();
    canvas.save();
    canvas.translate(timeAudioX,AndroidUtilities.dp(44) + namesOffset + mediaOffsetY);
    durationLayout.draw(canvas);
    canvas.restore();
    if (currentMessageObject.type != 0 && currentMessageObject.isContentUnread()) {
      Theme.chat_docBackPaint.setColor(Theme.getColor(currentMessageObject.isOutOwner() ? Theme.key_chat_outVoiceSeekbarFill : Theme.key_chat_inVoiceSeekbarFill));
      canvas.drawCircle(timeAudioX + timeWidthAudio + AndroidUtilities.dp(6),AndroidUtilities.dp(51) + namesOffset + mediaOffsetY,AndroidUtilities.dp(3),Theme.chat_docBackPaint);
    }
  }
  if (captionLayout != null) {
    if (currentMessageObject.type == 1 || documentAttachType == DOCUMENT_ATTACH_TYPE_VIDEO || currentMessageObject.type == 8) {
      captionX=photoImage.getImageX() + AndroidUtilities.dp(5) + captionOffsetX;
      captionY=photoImage.getImageY() + photoImage.getImageHeight() + AndroidUtilities.dp(6);
    }
 else     if (hasOldCaptionPreview) {
      captionX=backgroundDrawableLeft + AndroidUtilities.dp(currentMessageObject.isOutOwner() ? 11 : 17) + captionOffsetX;
      captionY=totalHeight - captionHeight - AndroidUtilities.dp(drawPinnedTop ? 9 : 10) - linkPreviewHeight - AndroidUtilities.dp(17);
    }
 else {
      captionX=backgroundDrawableLeft + AndroidUtilities.dp(currentMessageObject.isOutOwner() || mediaBackground || !mediaBackground && drawPinnedBottom ? 11 : 17) + captionOffsetX;
      captionY=totalHeight - captionHeight - AndroidUtilities.dp(drawPinnedTop ? 9 : 10);
    }
  }
  if (currentPosition == null) {
    drawCaptionLayout(canvas,false);
  }
  if (hasOldCaptionPreview) {
    int linkX;
    if (currentMessageObject.type == 1 || documentAttachType == DOCUMENT_ATTACH_TYPE_VIDEO || currentMessageObject.type == 8) {
      linkX=photoImage.getImageX() + AndroidUtilities.dp(5);
    }
 else {
      linkX=backgroundDrawableLeft + AndroidUtilities.dp(currentMessageObject.isOutOwner() ? 11 : 17);
    }
    int startY=totalHeight - AndroidUtilities.dp(drawPinnedTop ? 9 : 10) - linkPreviewHeight - AndroidUtilities.dp(8);
    int linkPreviewY=startY;
    Theme.chat_replyLinePaint.setColor(Theme.getColor(currentMessageObject.isOutOwner() ? Theme.key_chat_outPreviewLine : Theme.key_chat_inPreviewLine));
    canvas.drawRect(linkX,linkPreviewY - AndroidUtilities.dp(3),linkX + AndroidUtilities.dp(2),linkPreviewY + linkPreviewHeight,Theme.chat_replyLinePaint);
    if (siteNameLayout != null) {
      Theme.chat_replyNamePaint.setColor(Theme.getColor(currentMessageObject.isOutOwner() ? Theme.key_chat_outSiteNameText : Theme.key_chat_inSiteNameText));
      canvas.save();
      int x;
      if (siteNameRtl) {
        x=backgroundWidth - siteNameWidth - AndroidUtilities.dp(32);
      }
 else {
        x=(hasInvoicePreview ? 0 : AndroidUtilities.dp(10));
      }
      canvas.translate(linkX + x,linkPreviewY - AndroidUtilities.dp(3));
      siteNameLayout.draw(canvas);
      canvas.restore();
      linkPreviewY+=siteNameLayout.getLineBottom(siteNameLayout.getLineCount() - 1);
    }
    if (currentMessageObject.isOutOwner()) {
      Theme.chat_replyTextPaint.setColor(Theme.getColor(Theme.key_chat_messageTextOut));
    }
 else {
      Theme.chat_replyTextPaint.setColor(Theme.getColor(Theme.key_chat_messageTextIn));
    }
    if (descriptionLayout != null) {
      if (linkPreviewY != startY) {
        linkPreviewY+=AndroidUtilities.dp(2);
      }
      descriptionY=linkPreviewY - AndroidUtilities.dp(3);
      canvas.save();
      canvas.translate(linkX + AndroidUtilities.dp(10) + descriptionX,descriptionY);
      descriptionLayout.draw(canvas);
      canvas.restore();
    }
    drawTime=true;
  }
  if (documentAttachType == DOCUMENT_ATTACH_TYPE_DOCUMENT) {
    Drawable menuDrawable;
    if (currentMessageObject.isOutOwner()) {
      Theme.chat_docNamePaint.setColor(Theme.getColor(Theme.key_chat_outFileNameText));
      Theme.chat_infoPaint.setColor(Theme.getColor(isDrawSelectionBackground() ? Theme.key_chat_outFileInfoSelectedText : Theme.key_chat_outFileInfoText));
      Theme.chat_docBackPaint.setColor(Theme.getColor(isDrawSelectionBackground() ? Theme.key_chat_outFileBackgroundSelected : Theme.key_chat_outFileBackground));
      menuDrawable=isDrawSelectionBackground() ? Theme.chat_msgOutMenuSelectedDrawable : Theme.chat_msgOutMenuDrawable;
    }
 else {
      Theme.chat_docNamePaint.setColor(Theme.getColor(Theme.key_chat_inFileNameText));
      Theme.chat_infoPaint.setColor(Theme.getColor(isDrawSelectionBackground() ? Theme.key_chat_inFileInfoSelectedText : Theme.key_chat_inFileInfoText));
      Theme.chat_docBackPaint.setColor(Theme.getColor(isDrawSelectionBackground() ? Theme.key_chat_inFileBackgroundSelected : Theme.key_chat_inFileBackground));
      menuDrawable=isDrawSelectionBackground() ? Theme.chat_msgInMenuSelectedDrawable : Theme.chat_msgInMenuDrawable;
    }
    int x;
    int titleY;
    int subtitleY;
    if (drawPhotoImage) {
      if (currentMessageObject.type == 0) {
        setDrawableBounds(menuDrawable,otherX=photoImage.getImageX() + backgroundWidth - AndroidUtilities.dp(56),otherY=photoImage.getImageY() + AndroidUtilities.dp(1));
      }
 else {
        setDrawableBounds(menuDrawable,otherX=photoImage.getImageX() + backgroundWidth - AndroidUtilities.dp(40),otherY=photoImage.getImageY() + AndroidUtilities.dp(1));
      }
      x=photoImage.getImageX() + photoImage.getImageWidth() + AndroidUtilities.dp(10);
      titleY=photoImage.getImageY() + AndroidUtilities.dp(8);
      subtitleY=photoImage.getImageY() + (docTitleLayout != null ? docTitleLayout.getLineBottom(docTitleLayout.getLineCount() - 1) + AndroidUtilities.dp(13) : AndroidUtilities.dp(8));
      if (!imageDrawn) {
        if (currentMessageObject.isOutOwner()) {
          radialProgress.setColors(Theme.key_chat_outLoader,Theme.key_chat_outLoaderSelected,Theme.key_chat_outMediaIcon,Theme.key_chat_outMediaIconSelected);
          radialProgress.setProgressColor(Theme.getColor(isDrawSelectionBackground() ? Theme.key_chat_outFileProgressSelected : Theme.key_chat_outFileProgress));
          videoRadialProgress.setColors(Theme.key_chat_outLoader,Theme.key_chat_outLoaderSelected,Theme.key_chat_outMediaIcon,Theme.key_chat_outMediaIconSelected);
          videoRadialProgress.setProgressColor(Theme.getColor(isDrawSelectionBackground() ? Theme.key_chat_outFileProgressSelected : Theme.key_chat_outFileProgress));
        }
 else {
          radialProgress.setColors(Theme.key_chat_inLoader,Theme.key_chat_inLoaderSelected,Theme.key_chat_inMediaIcon,Theme.key_chat_inMediaIconSelected);
          radialProgress.setProgressColor(Theme.getColor(isDrawSelectionBackground() ? Theme.key_chat_inFileProgressSelected : Theme.key_chat_inFileProgress));
          videoRadialProgress.setColors(Theme.key_chat_inLoader,Theme.key_chat_inLoaderSelected,Theme.key_chat_inMediaIcon,Theme.key_chat_inMediaIconSelected);
          videoRadialProgress.setProgressColor(Theme.getColor(isDrawSelectionBackground() ? Theme.key_chat_inFileProgressSelected : Theme.key_chat_inFileProgress));
        }
        rect.set(photoImage.getImageX(),photoImage.getImageY(),photoImage.getImageX() + photoImage.getImageWidth(),photoImage.getImageY() + photoImage.getImageHeight());
        canvas.drawRoundRect(rect,AndroidUtilities.dp(3),AndroidUtilities.dp(3),Theme.chat_docBackPaint);
      }
 else {
        radialProgress.setColors(Theme.key_chat_mediaLoaderPhoto,Theme.key_chat_mediaLoaderPhotoSelected,Theme.key_chat_mediaLoaderPhotoIcon,Theme.key_chat_mediaLoaderPhotoIconSelected);
        radialProgress.setProgressColor(Theme.getColor(Theme.key_chat_mediaProgress));
        videoRadialProgress.setColors(Theme.key_chat_mediaLoaderPhoto,Theme.key_chat_mediaLoaderPhotoSelected,Theme.key_chat_mediaLoaderPhotoIcon,Theme.key_chat_mediaLoaderPhotoIconSelected);
        videoRadialProgress.setProgressColor(Theme.getColor(Theme.key_chat_mediaProgress));
        if (buttonState == -1 && radialProgress.getIcon() != MediaActionDrawable.ICON_NONE) {
          radialProgress.setIcon(MediaActionDrawable.ICON_NONE,true,true);
        }
      }
    }
 else {
      setDrawableBounds(menuDrawable,otherX=buttonX + backgroundWidth - AndroidUtilities.dp(currentMessageObject.type == 0 ? 58 : 48),otherY=buttonY - AndroidUtilities.dp(5));
      x=buttonX + AndroidUtilities.dp(53);
      titleY=buttonY + AndroidUtilities.dp(4);
      subtitleY=buttonY + AndroidUtilities.dp(27);
      if (docTitleLayout != null && docTitleLayout.getLineCount() > 1) {
        subtitleY+=(docTitleLayout.getLineCount() - 1) * AndroidUtilities.dp(16) + AndroidUtilities.dp(2);
      }
      if (currentMessageObject.isOutOwner()) {
        radialProgress.setProgressColor(Theme.getColor(isDrawSelectionBackground() || buttonPressed != 0 ? Theme.key_chat_outAudioSelectedProgress : Theme.key_chat_outAudioProgress));
        videoRadialProgress.setProgressColor(Theme.getColor(isDrawSelectionBackground() || videoButtonPressed != 0 ? Theme.key_chat_outAudioSelectedProgress : Theme.key_chat_outAudioProgress));
      }
 else {
        radialProgress.setProgressColor(Theme.getColor(isDrawSelectionBackground() || buttonPressed != 0 ? Theme.key_chat_inAudioSelectedProgress : Theme.key_chat_inAudioProgress));
        videoRadialProgress.setProgressColor(Theme.getColor(isDrawSelectionBackground() || videoButtonPressed != 0 ? Theme.key_chat_inAudioSelectedProgress : Theme.key_chat_inAudioProgress));
      }
    }
    menuDrawable.draw(canvas);
    try {
      if (docTitleLayout != null) {
        canvas.save();
        canvas.translate(x + docTitleOffsetX,titleY);
        docTitleLayout.draw(canvas);
        canvas.restore();
      }
    }
 catch (    Exception e) {
      FileLog.e(e);
    }
    try {
      if (infoLayout != null) {
        canvas.save();
        canvas.translate(x,subtitleY);
        infoLayout.draw(canvas);
        canvas.restore();
      }
    }
 catch (    Exception e) {
      FileLog.e(e);
    }
  }
  if (buttonState == -1 && currentMessageObject.needDrawBluredPreview() && !MediaController.getInstance().isPlayingMessage(currentMessageObject) && photoImage.getVisible()) {
    if (currentMessageObject.messageOwner.destroyTime != 0) {
      if (!currentMessageObject.isOutOwner()) {
        long msTime=System.currentTimeMillis() + ConnectionsManager.getInstance(currentAccount).getTimeDifference() * 1000;
        float progress=Math.max(0,(long)currentMessageObject.messageOwner.destroyTime * 1000 - msTime) / (currentMessageObject.messageOwner.ttl * 1000.0f);
        Theme.chat_deleteProgressPaint.setAlpha((int)(255 * controlsAlpha));
        canvas.drawArc(deleteProgressRect,-90,-360 * progress,true,Theme.chat_deleteProgressPaint);
        if (progress != 0) {
          int offset=AndroidUtilities.dp(2);
          invalidate((int)deleteProgressRect.left - offset,(int)deleteProgressRect.top - offset,(int)deleteProgressRect.right + offset * 2,(int)deleteProgressRect.bottom + offset * 2);
        }
      }
      updateSecretTimeText(currentMessageObject);
    }
  }
  if (currentMessageObject.type == 4 && !(currentMessageObject.messageOwner.media instanceof TLRPC.TL_messageMediaGeoLive) && currentMapProvider == 2 && photoImage.hasNotThumb()) {
    int w=(int)(Theme.chat_redLocationIcon.getIntrinsicWidth() * 0.8f);
    int h=(int)(Theme.chat_redLocationIcon.getIntrinsicHeight() * 0.8f);
    int x=photoImage.getImageX() + (photoImage.getImageWidth() - w) / 2;
    int y=photoImage.getImageY() + (photoImage.getImageHeight() / 2 - h);
    Theme.chat_redLocationIcon.setAlpha((int)(255 * photoImage.getCurrentAlpha()));
    Theme.chat_redLocationIcon.setBounds(x,y,x + w,y + h);
    Theme.chat_redLocationIcon.draw(canvas);
  }
  if (!botButtons.isEmpty()) {
    int addX;
    if (currentMessageObject.isOutOwner()) {
      addX=getMeasuredWidth() - widthForButtons - AndroidUtilities.dp(10);
    }
 else {
      addX=backgroundDrawableLeft + AndroidUtilities.dp(mediaBackground ? 1 : 7);
    }
    for (int a=0; a < botButtons.size(); a++) {
      BotButton button=botButtons.get(a);
      int y=button.y + layoutHeight - AndroidUtilities.dp(2);
      Theme.chat_systemDrawable.setColorFilter(a == pressedBotButton ? Theme.colorPressedFilter : Theme.colorFilter);
      Theme.chat_systemDrawable.setBounds(button.x + addX,y,button.x + addX + button.width,y + button.height);
      Theme.chat_systemDrawable.draw(canvas);
      canvas.save();
      canvas.translate(button.x + addX + AndroidUtilities.dp(5),y + (AndroidUtilities.dp(44) - button.title.getLineBottom(button.title.getLineCount() - 1)) / 2);
      button.title.draw(canvas);
      canvas.restore();
      if (button.button instanceof TLRPC.TL_keyboardButtonUrl) {
        int x=button.x + button.width - AndroidUtilities.dp(3) - Theme.chat_botLinkDrawalbe.getIntrinsicWidth() + addX;
        setDrawableBounds(Theme.chat_botLinkDrawalbe,x,y + AndroidUtilities.dp(3));
        Theme.chat_botLinkDrawalbe.draw(canvas);
      }
 else       if (button.button instanceof TLRPC.TL_keyboardButtonSwitchInline) {
        int x=button.x + button.width - AndroidUtilities.dp(3) - Theme.chat_botInlineDrawable.getIntrinsicWidth() + addX;
        setDrawableBounds(Theme.chat_botInlineDrawable,x,y + AndroidUtilities.dp(3));
        Theme.chat_botInlineDrawable.draw(canvas);
      }
 else       if (button.button instanceof TLRPC.TL_keyboardButtonCallback || button.button instanceof TLRPC.TL_keyboardButtonRequestGeoLocation || button.button instanceof TLRPC.TL_keyboardButtonGame || button.button instanceof TLRPC.TL_keyboardButtonBuy || button.button instanceof TLRPC.TL_keyboardButtonUrlAuth) {
        boolean drawProgress=(button.button instanceof TLRPC.TL_keyboardButtonCallback || button.button instanceof TLRPC.TL_keyboardButtonGame || button.button instanceof TLRPC.TL_keyboardButtonBuy || button.button instanceof TLRPC.TL_keyboardButtonUrlAuth) && SendMessagesHelper.getInstance(currentAccount).isSendingCallback(currentMessageObject,button.button) || button.button instanceof TLRPC.TL_keyboardButtonRequestGeoLocation && SendMessagesHelper.getInstance(currentAccount).isSendingCurrentLocation(currentMessageObject,button.button);
        if (drawProgress || !drawProgress && button.progressAlpha != 0) {
          Theme.chat_botProgressPaint.setAlpha(Math.min(255,(int)(button.progressAlpha * 255)));
          int x=button.x + button.width - AndroidUtilities.dp(9 + 3) + addX;
          rect.set(x,y + AndroidUtilities.dp(4),x + AndroidUtilities.dp(8),y + AndroidUtilities.dp(8 + 4));
          canvas.drawArc(rect,button.angle,220,false,Theme.chat_botProgressPaint);
          invalidate((int)rect.left - AndroidUtilities.dp(2),(int)rect.top - AndroidUtilities.dp(2),(int)rect.right + AndroidUtilities.dp(2),(int)rect.bottom + AndroidUtilities.dp(2));
          long newTime=System.currentTimeMillis();
          if (Math.abs(button.lastUpdateTime - System.currentTimeMillis()) < 1000) {
            long delta=(newTime - button.lastUpdateTime);
            float dt=360 * delta / 2000.0f;
            button.angle+=dt;
            button.angle-=360 * (button.angle / 360);
            if (drawProgress) {
              if (button.progressAlpha < 1.0f) {
                button.progressAlpha+=delta / 200.0f;
                if (button.progressAlpha > 1.0f) {
                  button.progressAlpha=1.0f;
                }
              }
            }
 else {
              if (button.progressAlpha > 0.0f) {
                button.progressAlpha-=delta / 200.0f;
                if (button.progressAlpha < 0.0f) {
                  button.progressAlpha=0.0f;
                }
              }
            }
          }
          button.lastUpdateTime=newTime;
        }
      }
    }
  }
}
