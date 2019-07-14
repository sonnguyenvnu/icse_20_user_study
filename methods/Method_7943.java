private boolean checkCaptionMotionEvent(MotionEvent event){
  if (!(currentCaption instanceof Spannable) || captionLayout == null) {
    return false;
  }
  if (event.getAction() == MotionEvent.ACTION_DOWN || (linkPreviewPressed || pressedLink != null) && event.getAction() == MotionEvent.ACTION_UP) {
    int x=(int)event.getX();
    int y=(int)event.getY();
    if (x >= captionX && x <= captionX + captionWidth && y >= captionY && y <= captionY + captionHeight) {
      if (event.getAction() == MotionEvent.ACTION_DOWN) {
        try {
          x-=captionX;
          y-=captionY;
          final int line=captionLayout.getLineForVertical(y);
          final int off=captionLayout.getOffsetForHorizontal(line,x);
          final float left=captionLayout.getLineLeft(line);
          if (left <= x && left + captionLayout.getLineWidth(line) >= x) {
            Spannable buffer=(Spannable)currentCaption;
            CharacterStyle[] link=buffer.getSpans(off,off,ClickableSpan.class);
            if (link == null || link.length == 0) {
              link=buffer.getSpans(off,off,URLSpanMono.class);
            }
            boolean ignore=false;
            if (link.length == 0 || link.length != 0 && link[0] instanceof URLSpanBotCommand && !URLSpanBotCommand.enabled) {
              ignore=true;
            }
            if (!ignore) {
              pressedLink=link[0];
              pressedLinkType=3;
              resetUrlPaths(false);
              try {
                LinkPath path=obtainNewUrlPath(false);
                int start=buffer.getSpanStart(pressedLink);
                path.setCurrentLayout(captionLayout,start,0);
                captionLayout.getSelectionPath(start,buffer.getSpanEnd(pressedLink),path);
              }
 catch (              Exception e) {
                FileLog.e(e);
              }
              if (currentMessagesGroup != null && getParent() != null) {
                ((ViewGroup)getParent()).invalidate();
              }
              invalidate();
              return true;
            }
          }
        }
 catch (        Exception e) {
          FileLog.e(e);
        }
      }
 else       if (pressedLinkType == 3) {
        delegate.didPressUrl(currentMessageObject,pressedLink,false);
        resetPressedLink(3);
        return true;
      }
    }
 else {
      resetPressedLink(3);
    }
  }
  return false;
}
