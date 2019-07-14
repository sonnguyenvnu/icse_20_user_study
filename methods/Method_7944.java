private boolean checkGameMotionEvent(MotionEvent event){
  if (!hasGamePreview) {
    return false;
  }
  int x=(int)event.getX();
  int y=(int)event.getY();
  if (event.getAction() == MotionEvent.ACTION_DOWN) {
    if (drawPhotoImage && drawImageButton && buttonState != -1 && x >= buttonX && x <= buttonX + AndroidUtilities.dp(48) && y >= buttonY && y <= buttonY + AndroidUtilities.dp(48) && radialProgress.getIcon() != MediaActionDrawable.ICON_NONE) {
      buttonPressed=1;
      invalidate();
      return true;
    }
 else     if (drawPhotoImage && photoImage.isInsideImage(x,y)) {
      gamePreviewPressed=true;
      return true;
    }
 else     if (descriptionLayout != null && y >= descriptionY) {
      try {
        x-=unmovedTextX + AndroidUtilities.dp(10) + descriptionX;
        y-=descriptionY;
        final int line=descriptionLayout.getLineForVertical(y);
        final int off=descriptionLayout.getOffsetForHorizontal(line,x);
        final float left=descriptionLayout.getLineLeft(line);
        if (left <= x && left + descriptionLayout.getLineWidth(line) >= x) {
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
 catch (            Exception e) {
              FileLog.e(e);
            }
            invalidate();
            return true;
          }
        }
      }
 catch (      Exception e) {
        FileLog.e(e);
      }
    }
  }
 else   if (event.getAction() == MotionEvent.ACTION_UP) {
    if (pressedLinkType == 2 || gamePreviewPressed || buttonPressed != 0) {
      if (buttonPressed != 0) {
        buttonPressed=0;
        playSoundEffect(SoundEffectConstants.CLICK);
        didPressButton(true,false);
        invalidate();
      }
 else       if (pressedLink != null) {
        if (pressedLink instanceof URLSpan) {
          Browser.openUrl(getContext(),((URLSpan)pressedLink).getURL());
        }
 else         if (pressedLink instanceof ClickableSpan) {
          ((ClickableSpan)pressedLink).onClick(this);
        }
        resetPressedLink(2);
      }
 else {
        gamePreviewPressed=false;
        for (int a=0; a < botButtons.size(); a++) {
          BotButton button=botButtons.get(a);
          if (button.button instanceof TLRPC.TL_keyboardButtonGame) {
            playSoundEffect(SoundEffectConstants.CLICK);
            delegate.didPressBotButton(this,button.button);
            invalidate();
            break;
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
  return false;
}
