private boolean checkLinkPreviewMotionEvent(MotionEvent event){
  if (currentMessageObject.type != 0 || !hasLinkPreview) {
    return false;
  }
  int x=(int)event.getX();
  int y=(int)event.getY();
  if (x >= unmovedTextX && x <= unmovedTextX + backgroundWidth && y >= textY + currentMessageObject.textHeight && y <= textY + currentMessageObject.textHeight + linkPreviewHeight + AndroidUtilities.dp(8 + (drawInstantView ? 46 : 0))) {
    if (event.getAction() == MotionEvent.ACTION_DOWN) {
      if (descriptionLayout != null && y >= descriptionY) {
        try {
          int checkX=x - (unmovedTextX + AndroidUtilities.dp(10) + descriptionX);
          int checkY=y - descriptionY;
          if (checkY <= descriptionLayout.getHeight()) {
            final int line=descriptionLayout.getLineForVertical(checkY);
            final int off=descriptionLayout.getOffsetForHorizontal(line,checkX);
            final float left=descriptionLayout.getLineLeft(line);
            if (left <= checkX && left + descriptionLayout.getLineWidth(line) >= checkX) {
              Spannable buffer=(Spannable)currentMessageObject.linkDescription;
              ClickableSpan[] link=buffer.getSpans(off,off,ClickableSpan.class);
              boolean ignore=false;
              if (link.length == 0 || link.length != 0 && link[0] instanceof URLSpanBotCommand && !URLSpanBotCommand.enabled) {
                ignore=true;
              }
              if (!ignore) {
                pressedLink=link[0];
                linkBlockNum=-10;
                pressedLinkType=2;
                resetUrlPaths(false);
                try {
                  LinkPath path=obtainNewUrlPath(false);
                  int start=buffer.getSpanStart(pressedLink);
                  path.setCurrentLayout(descriptionLayout,start,0);
                  descriptionLayout.getSelectionPath(start,buffer.getSpanEnd(pressedLink),path);
                }
 catch (                Exception e) {
                  FileLog.e(e);
                }
                invalidate();
                return true;
              }
            }
          }
        }
 catch (        Exception e) {
          FileLog.e(e);
        }
      }
      if (pressedLink == null) {
        int side=AndroidUtilities.dp(48);
        boolean area2=false;
        if (miniButtonState >= 0) {
          int offset=AndroidUtilities.dp(27);
          area2=x >= buttonX + offset && x <= buttonX + offset + side && y >= buttonY + offset && y <= buttonY + offset + side;
        }
        if (area2) {
          miniButtonPressed=1;
          invalidate();
          return true;
        }
 else         if (drawVideoImageButton && buttonState != -1 && x >= videoButtonX && x <= videoButtonX + AndroidUtilities.dp(26 + 8) + Math.max(infoWidth,docTitleWidth) && y >= videoButtonY && y <= videoButtonY + AndroidUtilities.dp(30)) {
          videoButtonPressed=1;
          invalidate();
          return true;
        }
 else         if (drawPhotoImage && drawImageButton && buttonState != -1 && (!checkOnlyButtonPressed && photoImage.isInsideImage(x,y) || x >= buttonX && x <= buttonX + AndroidUtilities.dp(48) && y >= buttonY && y <= buttonY + AndroidUtilities.dp(48) && radialProgress.getIcon() != MediaActionDrawable.ICON_NONE)) {
          buttonPressed=1;
          invalidate();
          return true;
        }
 else         if (drawInstantView) {
          instantPressed=true;
          if (Build.VERSION.SDK_INT >= 21 && selectorDrawable != null) {
            if (selectorDrawable.getBounds().contains(x,y)) {
              selectorDrawable.setState(pressedState);
              selectorDrawable.setHotspot(x,y);
              instantButtonPressed=true;
            }
          }
          invalidate();
          return true;
        }
 else         if (documentAttachType != DOCUMENT_ATTACH_TYPE_DOCUMENT && drawPhotoImage && photoImage.isInsideImage(x,y)) {
          linkPreviewPressed=true;
          TLRPC.WebPage webPage=currentMessageObject.messageOwner.media.webpage;
          if (documentAttachType == DOCUMENT_ATTACH_TYPE_GIF && buttonState == -1 && SharedConfig.autoplayGifs && (photoImage.getAnimation() == null || !TextUtils.isEmpty(webPage.embed_url))) {
            linkPreviewPressed=false;
            return false;
          }
          return true;
        }
      }
    }
 else     if (event.getAction() == MotionEvent.ACTION_UP) {
      if (instantPressed) {
        if (delegate != null) {
          delegate.didPressInstantButton(this,drawInstantViewType);
        }
        playSoundEffect(SoundEffectConstants.CLICK);
        if (Build.VERSION.SDK_INT >= 21 && selectorDrawable != null) {
          selectorDrawable.setState(StateSet.NOTHING);
        }
        instantPressed=instantButtonPressed=false;
        invalidate();
      }
 else       if (pressedLinkType == 2 || buttonPressed != 0 || miniButtonPressed != 0 || videoButtonPressed != 0 || linkPreviewPressed) {
        if (videoButtonPressed == 1) {
          videoButtonPressed=0;
          playSoundEffect(SoundEffectConstants.CLICK);
          didPressButton(true,true);
          invalidate();
        }
 else         if (buttonPressed != 0) {
          buttonPressed=0;
          playSoundEffect(SoundEffectConstants.CLICK);
          if (drawVideoImageButton) {
            didClickedImage();
          }
 else {
            didPressButton(true,false);
          }
          invalidate();
        }
 else         if (miniButtonPressed != 0) {
          miniButtonPressed=0;
          playSoundEffect(SoundEffectConstants.CLICK);
          didPressMiniButton(true);
          invalidate();
        }
 else         if (pressedLink != null) {
          if (pressedLink instanceof URLSpan) {
            Browser.openUrl(getContext(),((URLSpan)pressedLink).getURL());
          }
 else           if (pressedLink instanceof ClickableSpan) {
            ((ClickableSpan)pressedLink).onClick(this);
          }
          resetPressedLink(2);
        }
 else {
          if (documentAttachType == DOCUMENT_ATTACH_TYPE_ROUND) {
            if (!MediaController.getInstance().isPlayingMessage(currentMessageObject) || MediaController.getInstance().isMessagePaused()) {
              delegate.needPlayMessage(currentMessageObject);
            }
 else {
              MediaController.getInstance().pauseMessage(currentMessageObject);
            }
          }
 else           if (documentAttachType == DOCUMENT_ATTACH_TYPE_GIF && drawImageButton) {
            if (buttonState == -1) {
              if (SharedConfig.autoplayGifs) {
                delegate.didPressImage(this,lastTouchX,lastTouchY);
              }
 else {
                buttonState=2;
                currentMessageObject.gifState=1;
                photoImage.setAllowStartAnimation(false);
                photoImage.stopAnimation();
                radialProgress.setIcon(getIconForCurrentState(),false,true);
                invalidate();
                playSoundEffect(SoundEffectConstants.CLICK);
              }
            }
 else             if (buttonState == 2 || buttonState == 0) {
              didPressButton(true,false);
              playSoundEffect(SoundEffectConstants.CLICK);
            }
          }
 else {
            TLRPC.WebPage webPage=currentMessageObject.messageOwner.media.webpage;
            if (webPage != null && !TextUtils.isEmpty(webPage.embed_url)) {
              delegate.needOpenWebView(webPage.embed_url,webPage.site_name,webPage.title,webPage.url,webPage.embed_width,webPage.embed_height);
            }
 else             if (buttonState == -1 || buttonState == 3) {
              delegate.didPressImage(this,lastTouchX,lastTouchY);
              playSoundEffect(SoundEffectConstants.CLICK);
            }
 else             if (webPage != null) {
              Browser.openUrl(getContext(),webPage.url);
            }
          }
          resetPressedLink(2);
          return true;
        }
      }
 else {
        resetPressedLink(2);
      }
    }
 else     if (event.getAction() == MotionEvent.ACTION_MOVE) {
      if (instantButtonPressed && Build.VERSION.SDK_INT >= 21 && selectorDrawable != null) {
        selectorDrawable.setHotspot(x,y);
      }
    }
  }
  return false;
}
