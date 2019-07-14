public void drawOverlays(Canvas canvas){
  long newAnimationTime=SystemClock.uptimeMillis();
  long animationDt=newAnimationTime - lastAnimationTime;
  if (animationDt > 17) {
    animationDt=17;
  }
  lastAnimationTime=newAnimationTime;
  if (currentMessageObject.hadAnimationNotReadyLoading && photoImage.getVisible() && !currentMessageObject.needDrawBluredPreview() && (documentAttachType == DOCUMENT_ATTACH_TYPE_ROUND || documentAttachType == DOCUMENT_ATTACH_TYPE_VIDEO || documentAttachType == DOCUMENT_ATTACH_TYPE_GIF)) {
    AnimatedFileDrawable animation=photoImage.getAnimation();
    if (animation != null && animation.hasBitmap()) {
      currentMessageObject.hadAnimationNotReadyLoading=false;
      updateButtonState(false,true,false);
    }
  }
  if (currentMessageObject.type == 1 || documentAttachType == DOCUMENT_ATTACH_TYPE_VIDEO || documentAttachType == DOCUMENT_ATTACH_TYPE_GIF) {
    if (photoImage.getVisible()) {
      if (!currentMessageObject.needDrawBluredPreview()) {
        if (documentAttachType == DOCUMENT_ATTACH_TYPE_VIDEO) {
          int oldAlpha=((BitmapDrawable)Theme.chat_msgMediaMenuDrawable).getPaint().getAlpha();
          if (drawPhotoCheckBox) {
            Theme.chat_msgMediaMenuDrawable.setAlpha((int)(oldAlpha * controlsAlpha * (1.0f - checkBoxAnimationProgress)));
          }
 else {
            Theme.chat_msgMediaMenuDrawable.setAlpha((int)(oldAlpha * controlsAlpha));
          }
          setDrawableBounds(Theme.chat_msgMediaMenuDrawable,otherX=photoImage.getImageX() + photoImage.getImageWidth() - AndroidUtilities.dp(14),otherY=photoImage.getImageY() + AndroidUtilities.dp(8.1f));
          Theme.chat_msgMediaMenuDrawable.draw(canvas);
          Theme.chat_msgMediaMenuDrawable.setAlpha(oldAlpha);
        }
      }
      boolean playing=MediaController.getInstance().isPlayingMessage(currentMessageObject);
      if (animatingNoSoundPlaying != playing) {
        animatingNoSoundPlaying=playing;
        animatingNoSound=playing ? 1 : 2;
        animatingNoSoundProgress=playing ? 1.0f : 0.0f;
      }
      if (buttonState == 1 || buttonState == 2 || buttonState == 0 || buttonState == 3 || buttonState == -1 || currentMessageObject.needDrawBluredPreview()) {
        if (autoPlayingMedia) {
          updatePlayingMessageProgress();
        }
        if (infoLayout != null && (!forceNotDrawTime || autoPlayingMedia || drawVideoImageButton)) {
          float alpha=currentMessageObject.needDrawBluredPreview() && docTitleLayout == null ? 0 : animatingDrawVideoImageButtonProgress;
          Theme.chat_infoPaint.setColor(Theme.getColor(Theme.key_chat_mediaInfoText));
          int x1=photoImage.getImageX() + AndroidUtilities.dp(4);
          int y1=photoImage.getImageY() + AndroidUtilities.dp(4);
          int imageW;
          if (autoPlayingMedia && (!playing || animatingNoSound != 0)) {
            imageW=(int)((Theme.chat_msgNoSoundDrawable.getIntrinsicWidth() + AndroidUtilities.dp(4)) * animatingNoSoundProgress);
          }
 else {
            imageW=0;
          }
          int w=(int)Math.ceil(infoWidth + AndroidUtilities.dp(8) + imageW + (Math.max(infoWidth + imageW,docTitleWidth) + (canStreamVideo ? AndroidUtilities.dp(32) : 0) - infoWidth - imageW) * alpha);
          if (alpha != 0 && docTitleLayout == null) {
            alpha=0;
          }
          rect.set(x1,y1,x1 + w,y1 + AndroidUtilities.dp(16.5f + 15.5f * alpha));
          int oldAlpha=Theme.chat_timeBackgroundPaint.getAlpha();
          Theme.chat_timeBackgroundPaint.setAlpha((int)(oldAlpha * controlsAlpha));
          canvas.drawRoundRect(rect,AndroidUtilities.dp(4),AndroidUtilities.dp(4),Theme.chat_timeBackgroundPaint);
          Theme.chat_timeBackgroundPaint.setAlpha(oldAlpha);
          Theme.chat_infoPaint.setAlpha((int)(255 * controlsAlpha));
          canvas.save();
          canvas.translate(noSoundCenterX=photoImage.getImageX() + AndroidUtilities.dp(8 + (canStreamVideo ? 30 * alpha : 0)),photoImage.getImageY() + AndroidUtilities.dp(5.5f + 0.2f * alpha));
          if (infoLayout != null) {
            infoLayout.draw(canvas);
          }
          if (alpha > 0 && docTitleLayout != null) {
            canvas.save();
            Theme.chat_infoPaint.setAlpha((int)(255 * controlsAlpha * alpha));
            canvas.translate(0,AndroidUtilities.dp(14.3f * alpha));
            docTitleLayout.draw(canvas);
            canvas.restore();
          }
          if (imageW != 0) {
            Theme.chat_msgNoSoundDrawable.setAlpha((int)(255 * animatingNoSoundProgress * animatingNoSoundProgress * controlsAlpha));
            canvas.translate(infoWidth + AndroidUtilities.dp(4),0);
            int size=AndroidUtilities.dp(14 * animatingNoSoundProgress);
            int y=(AndroidUtilities.dp(14) - size) / 2;
            Theme.chat_msgNoSoundDrawable.setBounds(0,y,size,y + size);
            Theme.chat_msgNoSoundDrawable.draw(canvas);
            noSoundCenterX+=infoWidth + AndroidUtilities.dp(4) + size / 2;
          }
          canvas.restore();
          Theme.chat_infoPaint.setAlpha(255);
        }
      }
      if (animatingDrawVideoImageButton == 1) {
        animatingDrawVideoImageButtonProgress-=animationDt / 160.0f;
        if (animatingDrawVideoImageButtonProgress <= 0) {
          animatingDrawVideoImageButtonProgress=0;
          animatingDrawVideoImageButton=0;
        }
        invalidate();
      }
 else       if (animatingDrawVideoImageButton == 2) {
        animatingDrawVideoImageButtonProgress+=animationDt / 160.0f;
        if (animatingDrawVideoImageButtonProgress >= 1) {
          animatingDrawVideoImageButtonProgress=1;
          animatingDrawVideoImageButton=0;
        }
        invalidate();
      }
      if (animatingNoSound == 1) {
        animatingNoSoundProgress-=animationDt / 180.0f;
        if (animatingNoSoundProgress <= 0.0f) {
          animatingNoSoundProgress=0.0f;
          animatingNoSound=0;
        }
        invalidate();
      }
 else       if (animatingNoSound == 2) {
        animatingNoSoundProgress+=animationDt / 180.0f;
        if (animatingNoSoundProgress >= 1.0f) {
          animatingNoSoundProgress=1.0f;
          animatingNoSound=0;
        }
        invalidate();
      }
    }
  }
 else   if (currentMessageObject.type == 4) {
    if (docTitleLayout != null) {
      if (currentMessageObject.isOutOwner()) {
        Theme.chat_locationTitlePaint.setColor(Theme.getColor(Theme.key_chat_messageTextOut));
        Theme.chat_locationAddressPaint.setColor(Theme.getColor(isDrawSelectionBackground() ? Theme.key_chat_outVenueInfoSelectedText : Theme.key_chat_outVenueInfoText));
      }
 else {
        Theme.chat_locationTitlePaint.setColor(Theme.getColor(Theme.key_chat_messageTextIn));
        Theme.chat_locationAddressPaint.setColor(Theme.getColor(isDrawSelectionBackground() ? Theme.key_chat_inVenueInfoSelectedText : Theme.key_chat_inVenueInfoText));
      }
      if (currentMessageObject.messageOwner.media instanceof TLRPC.TL_messageMediaGeoLive) {
        int cy=photoImage.getImageY2() + AndroidUtilities.dp(30);
        if (!locationExpired) {
          forceNotDrawTime=true;
          float progress=1.0f - Math.abs(ConnectionsManager.getInstance(currentAccount).getCurrentTime() - currentMessageObject.messageOwner.date) / (float)currentMessageObject.messageOwner.media.period;
          rect.set(photoImage.getImageX2() - AndroidUtilities.dp(43),cy - AndroidUtilities.dp(15),photoImage.getImageX2() - AndroidUtilities.dp(13),cy + AndroidUtilities.dp(15));
          if (currentMessageObject.isOutOwner()) {
            Theme.chat_radialProgress2Paint.setColor(Theme.getColor(Theme.key_chat_outInstant));
            Theme.chat_livePaint.setColor(Theme.getColor(Theme.key_chat_outInstant));
          }
 else {
            Theme.chat_radialProgress2Paint.setColor(Theme.getColor(Theme.key_chat_inInstant));
            Theme.chat_livePaint.setColor(Theme.getColor(Theme.key_chat_inInstant));
          }
          Theme.chat_radialProgress2Paint.setAlpha(50);
          canvas.drawCircle(rect.centerX(),rect.centerY(),AndroidUtilities.dp(15),Theme.chat_radialProgress2Paint);
          Theme.chat_radialProgress2Paint.setAlpha(255);
          canvas.drawArc(rect,-90,-360 * progress,false,Theme.chat_radialProgress2Paint);
          String text=LocaleController.formatLocationLeftTime(Math.abs(currentMessageObject.messageOwner.media.period - (ConnectionsManager.getInstance(currentAccount).getCurrentTime() - currentMessageObject.messageOwner.date)));
          float w=Theme.chat_livePaint.measureText(text);
          canvas.drawText(text,rect.centerX() - w / 2,cy + AndroidUtilities.dp(4),Theme.chat_livePaint);
          canvas.save();
          canvas.translate(photoImage.getImageX() + AndroidUtilities.dp(10),photoImage.getImageY2() + AndroidUtilities.dp(10));
          docTitleLayout.draw(canvas);
          canvas.translate(0,AndroidUtilities.dp(23));
          infoLayout.draw(canvas);
          canvas.restore();
        }
        int cx=photoImage.getImageX() + photoImage.getImageWidth() / 2 - AndroidUtilities.dp(31);
        cy=photoImage.getImageY() + photoImage.getImageHeight() / 2 - AndroidUtilities.dp(38);
        setDrawableBounds(Theme.chat_msgAvatarLiveLocationDrawable,cx,cy);
        Theme.chat_msgAvatarLiveLocationDrawable.draw(canvas);
        locationImageReceiver.setImageCoords(cx + AndroidUtilities.dp(5.0f),cy + AndroidUtilities.dp(5.0f),AndroidUtilities.dp(52),AndroidUtilities.dp(52));
        locationImageReceiver.draw(canvas);
      }
 else {
        canvas.save();
        canvas.translate(photoImage.getImageX() + AndroidUtilities.dp(6),photoImage.getImageY2() + AndroidUtilities.dp(8));
        docTitleLayout.draw(canvas);
        if (infoLayout != null) {
          canvas.translate(0,AndroidUtilities.dp(21));
          infoLayout.draw(canvas);
        }
        canvas.restore();
      }
    }
  }
 else   if (currentMessageObject.type == 16) {
    if (currentMessageObject.isOutOwner()) {
      Theme.chat_audioTitlePaint.setColor(Theme.getColor(Theme.key_chat_messageTextOut));
      Theme.chat_contactPhonePaint.setColor(Theme.getColor(isDrawSelectionBackground() ? Theme.key_chat_outTimeSelectedText : Theme.key_chat_outTimeText));
    }
 else {
      Theme.chat_audioTitlePaint.setColor(Theme.getColor(Theme.key_chat_messageTextIn));
      Theme.chat_contactPhonePaint.setColor(Theme.getColor(isDrawSelectionBackground() ? Theme.key_chat_inTimeSelectedText : Theme.key_chat_inTimeText));
    }
    forceNotDrawTime=true;
    int x;
    if (currentMessageObject.isOutOwner()) {
      x=layoutWidth - backgroundWidth + AndroidUtilities.dp(16);
    }
 else {
      if (isChat && currentMessageObject.needDrawAvatar()) {
        x=AndroidUtilities.dp(74);
      }
 else {
        x=AndroidUtilities.dp(25);
      }
    }
    otherX=x;
    if (titleLayout != null) {
      canvas.save();
      canvas.translate(x,AndroidUtilities.dp(12) + namesOffset);
      titleLayout.draw(canvas);
      canvas.restore();
    }
    if (docTitleLayout != null) {
      canvas.save();
      canvas.translate(x + AndroidUtilities.dp(19),AndroidUtilities.dp(37) + namesOffset);
      docTitleLayout.draw(canvas);
      canvas.restore();
    }
    Drawable icon;
    Drawable phone;
    if (currentMessageObject.isOutOwner()) {
      icon=Theme.chat_msgCallUpGreenDrawable;
      phone=isDrawSelectionBackground() || otherPressed ? Theme.chat_msgOutCallSelectedDrawable : Theme.chat_msgOutCallDrawable;
    }
 else {
      TLRPC.PhoneCallDiscardReason reason=currentMessageObject.messageOwner.action.reason;
      if (reason instanceof TLRPC.TL_phoneCallDiscardReasonMissed || reason instanceof TLRPC.TL_phoneCallDiscardReasonBusy) {
        icon=Theme.chat_msgCallDownRedDrawable;
      }
 else {
        icon=Theme.chat_msgCallDownGreenDrawable;
      }
      phone=isDrawSelectionBackground() || otherPressed ? Theme.chat_msgInCallSelectedDrawable : Theme.chat_msgInCallDrawable;
    }
    setDrawableBounds(icon,x - AndroidUtilities.dp(3),AndroidUtilities.dp(36) + namesOffset);
    icon.draw(canvas);
    setDrawableBounds(phone,x + AndroidUtilities.dp(205),otherY=AndroidUtilities.dp(22));
    phone.draw(canvas);
  }
 else   if (currentMessageObject.type == MessageObject.TYPE_POLL) {
    if (currentMessageObject.isOutOwner()) {
      int color=Theme.getColor(Theme.key_chat_messageTextOut);
      Theme.chat_audioTitlePaint.setColor(color);
      Theme.chat_audioPerformerPaint.setColor(color);
      Theme.chat_instantViewPaint.setColor(color);
      color=Theme.getColor(isDrawSelectionBackground() ? Theme.key_chat_outTimeSelectedText : Theme.key_chat_outTimeText);
      Theme.chat_timePaint.setColor(color);
      Theme.chat_livePaint.setColor(color);
    }
 else {
      int color=Theme.getColor(Theme.key_chat_messageTextIn);
      Theme.chat_audioTitlePaint.setColor(color);
      Theme.chat_audioPerformerPaint.setColor(color);
      Theme.chat_instantViewPaint.setColor(color);
      color=Theme.getColor(isDrawSelectionBackground() ? Theme.key_chat_inTimeSelectedText : Theme.key_chat_inTimeText);
      Theme.chat_timePaint.setColor(color);
      Theme.chat_livePaint.setColor(color);
    }
    int x;
    if (currentMessageObject.isOutOwner()) {
      x=layoutWidth - backgroundWidth + AndroidUtilities.dp(11);
    }
 else {
      if (isChat && currentMessageObject.needDrawAvatar()) {
        x=AndroidUtilities.dp(68);
      }
 else {
        x=AndroidUtilities.dp(20);
      }
    }
    if (titleLayout != null) {
      canvas.save();
      canvas.translate(x,AndroidUtilities.dp(15) + namesOffset);
      titleLayout.draw(canvas);
      canvas.restore();
    }
    if (docTitleLayout != null) {
      canvas.save();
      canvas.translate(x + docTitleOffsetX,(titleLayout != null ? titleLayout.getHeight() : 0) + AndroidUtilities.dp(20) + namesOffset);
      docTitleLayout.draw(canvas);
      canvas.restore();
    }
    if (Build.VERSION.SDK_INT >= 21 && selectorDrawable != null) {
      selectorDrawable.draw(canvas);
    }
    int lastVoteY=0;
    for (int a=0, N=pollButtons.size(); a < N; a++) {
      PollButton button=pollButtons.get(a);
      button.x=x;
      canvas.save();
      canvas.translate(x + AndroidUtilities.dp(34),button.y + namesOffset);
      button.title.draw(canvas);
      int alpha=(int)(animatePollAnswerAlpha ? 255 * Math.min((pollUnvoteInProgress ? 1.0f - pollAnimationProgress : pollAnimationProgress) / 0.3f,1.0f) : 255);
      if (pollVoted || pollClosed || animatePollAnswerAlpha) {
        Theme.chat_docBackPaint.setColor(Theme.getColor(currentMessageObject.isOutOwner() ? Theme.key_chat_outAudioSeekbarFill : Theme.key_chat_inAudioSeekbarFill));
        if (animatePollAnswerAlpha) {
          float oldAlpha=Theme.chat_instantViewPaint.getAlpha() / 255.0f;
          Theme.chat_instantViewPaint.setAlpha((int)(alpha * oldAlpha));
          oldAlpha=Theme.chat_docBackPaint.getAlpha() / 255.0f;
          Theme.chat_docBackPaint.setAlpha((int)(alpha * oldAlpha));
        }
        int currentPercent=(int)Math.ceil(button.prevPercent + (button.percent - button.prevPercent) * pollAnimationProgress);
        String text=String.format("%d%%",currentPercent);
        int width=(int)Math.ceil(Theme.chat_instantViewPaint.measureText(text));
        canvas.drawText(text,-AndroidUtilities.dp(7) - width,AndroidUtilities.dp(14),Theme.chat_instantViewPaint);
        width=backgroundWidth - AndroidUtilities.dp(76);
        float currentPercentProgress=button.prevPercentProgress + (button.percentProgress - button.prevPercentProgress) * pollAnimationProgress;
        instantButtonRect.set(0,button.height + AndroidUtilities.dp(6),width * currentPercentProgress,button.height + AndroidUtilities.dp(11));
        canvas.drawRoundRect(instantButtonRect,AndroidUtilities.dp(2),AndroidUtilities.dp(2),Theme.chat_docBackPaint);
      }
      if (!pollVoted && !pollClosed || animatePollAnswerAlpha) {
        if (isDrawSelectionBackground()) {
          Theme.chat_replyLinePaint.setColor(Theme.getColor(currentMessageObject.isOutOwner() ? Theme.key_chat_outVoiceSeekbarSelected : Theme.key_chat_inVoiceSeekbarSelected));
        }
 else {
          Theme.chat_replyLinePaint.setColor(Theme.getColor(currentMessageObject.isOutOwner() ? Theme.key_chat_outVoiceSeekbar : Theme.key_chat_inVoiceSeekbar));
        }
        if (animatePollAnswerAlpha) {
          float oldAlpha=Theme.chat_replyLinePaint.getAlpha() / 255.0f;
          Theme.chat_replyLinePaint.setAlpha((int)((255 - alpha) * oldAlpha));
        }
        canvas.drawLine(-AndroidUtilities.dp(2),button.height + AndroidUtilities.dp(13),backgroundWidth - AndroidUtilities.dp(56),button.height + AndroidUtilities.dp(13),Theme.chat_replyLinePaint);
        if (pollVoteInProgress && a == pollVoteInProgressNum) {
          Theme.chat_instantViewRectPaint.setColor(Theme.getColor(currentMessageObject.isOutOwner() ? Theme.key_chat_outAudioSeekbarFill : Theme.key_chat_inAudioSeekbarFill));
          if (animatePollAnswerAlpha) {
            float oldAlpha=Theme.chat_instantViewRectPaint.getAlpha() / 255.0f;
            Theme.chat_instantViewRectPaint.setAlpha((int)((255 - alpha) * oldAlpha));
          }
          instantButtonRect.set(-AndroidUtilities.dp(23) - AndroidUtilities.dp(8.5f),AndroidUtilities.dp(9) - AndroidUtilities.dp(8.5f),-AndroidUtilities.dp(23) + AndroidUtilities.dp(8.5f),AndroidUtilities.dp(9) + AndroidUtilities.dp(8.5f));
          canvas.drawArc(instantButtonRect,voteRadOffset,voteCurrentCircleLength,false,Theme.chat_instantViewRectPaint);
        }
 else {
          if (currentMessageObject.isOutOwner()) {
            Theme.chat_instantViewRectPaint.setColor(Theme.getColor(isDrawSelectionBackground() ? Theme.key_chat_outMenuSelected : Theme.key_chat_outMenu));
          }
 else {
            Theme.chat_instantViewRectPaint.setColor(Theme.getColor(isDrawSelectionBackground() ? Theme.key_chat_inMenuSelected : Theme.key_chat_inMenu));
          }
          if (animatePollAnswerAlpha) {
            float oldAlpha=Theme.chat_instantViewRectPaint.getAlpha() / 255.0f;
            Theme.chat_instantViewRectPaint.setAlpha((int)((255 - alpha) * oldAlpha));
          }
          canvas.drawCircle(-AndroidUtilities.dp(23),AndroidUtilities.dp(9),AndroidUtilities.dp(8.5f),Theme.chat_instantViewRectPaint);
        }
      }
      canvas.restore();
      if (a == N - 1) {
        lastVoteY=button.y + namesOffset + button.height;
      }
    }
    if (infoLayout != null) {
      canvas.save();
      canvas.translate(x + infoX,lastVoteY + AndroidUtilities.dp(22));
      infoLayout.draw(canvas);
      canvas.restore();
    }
    updatePollAnimations();
  }
 else   if (currentMessageObject.type == 12) {
    if (currentMessageObject.isOutOwner()) {
      Theme.chat_contactNamePaint.setColor(Theme.getColor(Theme.key_chat_outContactNameText));
      Theme.chat_contactPhonePaint.setColor(Theme.getColor(isDrawSelectionBackground() ? Theme.key_chat_outContactPhoneSelectedText : Theme.key_chat_outContactPhoneText));
    }
 else {
      Theme.chat_contactNamePaint.setColor(Theme.getColor(Theme.key_chat_inContactNameText));
      Theme.chat_contactPhonePaint.setColor(Theme.getColor(isDrawSelectionBackground() ? Theme.key_chat_inContactPhoneSelectedText : Theme.key_chat_inContactPhoneText));
    }
    if (titleLayout != null) {
      canvas.save();
      canvas.translate(photoImage.getImageX() + photoImage.getImageWidth() + AndroidUtilities.dp(9),AndroidUtilities.dp(16) + namesOffset);
      titleLayout.draw(canvas);
      canvas.restore();
    }
    if (docTitleLayout != null) {
      canvas.save();
      canvas.translate(photoImage.getImageX() + photoImage.getImageWidth() + AndroidUtilities.dp(9),AndroidUtilities.dp(39) + namesOffset);
      docTitleLayout.draw(canvas);
      canvas.restore();
    }
    Drawable menuDrawable;
    if (currentMessageObject.isOutOwner()) {
      menuDrawable=isDrawSelectionBackground() ? Theme.chat_msgOutMenuSelectedDrawable : Theme.chat_msgOutMenuDrawable;
    }
 else {
      menuDrawable=isDrawSelectionBackground() ? Theme.chat_msgInMenuSelectedDrawable : Theme.chat_msgInMenuDrawable;
    }
    setDrawableBounds(menuDrawable,otherX=photoImage.getImageX() + backgroundWidth - AndroidUtilities.dp(48),otherY=photoImage.getImageY() - AndroidUtilities.dp(5));
    menuDrawable.draw(canvas);
    if (drawInstantView) {
      int textX=photoImage.getImageX() - AndroidUtilities.dp(2);
      Drawable instantDrawable;
      int instantY=getMeasuredHeight() - AndroidUtilities.dp(36 + 28);
      Paint backPaint=Theme.chat_instantViewRectPaint;
      if (currentMessageObject.isOutOwner()) {
        Theme.chat_instantViewPaint.setColor(Theme.getColor(Theme.key_chat_outPreviewInstantText));
        backPaint.setColor(Theme.getColor(Theme.key_chat_outPreviewInstantText));
      }
 else {
        Theme.chat_instantViewPaint.setColor(Theme.getColor(Theme.key_chat_inPreviewInstantText));
        backPaint.setColor(Theme.getColor(Theme.key_chat_inPreviewInstantText));
      }
      if (Build.VERSION.SDK_INT >= 21) {
        selectorDrawable.setBounds(textX,instantY,textX + instantWidth,instantY + AndroidUtilities.dp(36));
        selectorDrawable.draw(canvas);
      }
      instantButtonRect.set(textX,instantY,textX + instantWidth,instantY + AndroidUtilities.dp(36));
      canvas.drawRoundRect(instantButtonRect,AndroidUtilities.dp(6),AndroidUtilities.dp(6),backPaint);
      if (instantViewLayout != null) {
        canvas.save();
        canvas.translate(textX + instantTextX,instantY + AndroidUtilities.dp(10.5f));
        instantViewLayout.draw(canvas);
        canvas.restore();
      }
    }
  }
  if (drawImageButton && photoImage.getVisible()) {
    if (controlsAlpha != 1.0f) {
      radialProgress.setOverrideAlpha(controlsAlpha);
    }
    radialProgress.draw(canvas);
  }
  if ((drawVideoImageButton || animatingDrawVideoImageButton != 0) && photoImage.getVisible()) {
    if (controlsAlpha != 1.0f) {
      videoRadialProgress.setOverrideAlpha(controlsAlpha);
    }
    videoRadialProgress.draw(canvas);
  }
  if (drawPhotoCheckBox) {
    int size=AndroidUtilities.dp(21);
    photoCheckBox.setColor(null,null,currentMessageObject.isOutOwner() ? Theme.key_chat_outBubbleSelected : Theme.key_chat_inBubbleSelected);
    photoCheckBox.setBounds(photoImage.getImageX2() - AndroidUtilities.dp(21 + 4),photoImage.getImageY() + AndroidUtilities.dp(4),size,size);
    photoCheckBox.draw(canvas);
  }
}
