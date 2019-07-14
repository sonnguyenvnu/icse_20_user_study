private boolean checkLayoutForLinks(MotionEvent event,View parentView,DrawingText drawingText,int layoutX,int layoutY){
  if (pageSwitchAnimation != null || parentView == null || drawingText == null) {
    return false;
  }
  StaticLayout layout=drawingText.textLayout;
  int x=(int)event.getX();
  int y=(int)event.getY();
  boolean removeLink=false;
  if (event.getAction() == MotionEvent.ACTION_DOWN) {
    float width=0;
    float left=Integer.MAX_VALUE;
    for (int a=0, N=layout.getLineCount(); a < N; a++) {
      width=Math.max(layout.getLineWidth(a),width);
      left=Math.min(layout.getLineLeft(a),left);
    }
    if (x >= layoutX + left && x <= left + layoutX + width && y >= layoutY && y <= layoutY + layout.getHeight()) {
      pressedLinkOwnerLayout=drawingText;
      pressedLinkOwnerView=parentView;
      pressedLayoutY=layoutY;
      CharSequence text=layout.getText();
      if (text instanceof Spannable) {
        try {
          int checkX=x - layoutX;
          int checkY=y - layoutY;
          final int line=layout.getLineForVertical(checkY);
          final int off=layout.getOffsetForHorizontal(line,checkX);
          left=layout.getLineLeft(line);
          if (left <= checkX && left + layout.getLineWidth(line) >= checkX) {
            Spannable buffer=(Spannable)layout.getText();
            TextPaintUrlSpan[] link=buffer.getSpans(off,off,TextPaintUrlSpan.class);
            if (link != null && link.length > 0) {
              pressedLink=link[0];
              int pressedStart=buffer.getSpanStart(pressedLink);
              int pressedEnd=buffer.getSpanEnd(pressedLink);
              for (int a=1; a < link.length; a++) {
                TextPaintUrlSpan span=link[a];
                int start=buffer.getSpanStart(span);
                int end=buffer.getSpanEnd(span);
                if (pressedStart > start || end > pressedEnd) {
                  pressedLink=span;
                  pressedStart=start;
                  pressedEnd=end;
                }
              }
              try {
                urlPath.setUseRoundRect(true);
                urlPath.setCurrentLayout(layout,pressedStart,0);
                int shift=pressedLink.getTextPaint() != null ? pressedLink.getTextPaint().baselineShift : 0;
                urlPath.setBaselineShift(shift != 0 ? shift + AndroidUtilities.dp(shift > 0 ? 5 : -2) : 0);
                layout.getSelectionPath(pressedStart,pressedEnd,urlPath);
                parentView.invalidate();
              }
 catch (              Exception e) {
                FileLog.e(e);
              }
            }
          }
        }
 catch (        Exception e) {
          FileLog.e(e);
        }
      }
    }
  }
 else   if (event.getAction() == MotionEvent.ACTION_UP) {
    if (pressedLink != null) {
      removeLink=true;
      String url=pressedLink.getUrl();
      if (url != null) {
        if (linkSheet != null) {
          linkSheet.dismiss();
          linkSheet=null;
        }
        int index;
        boolean isAnchor=false;
        String anchor;
        if ((index=url.lastIndexOf('#')) != -1) {
          String webPageUrl;
          if (!TextUtils.isEmpty(currentPage.cached_page.url)) {
            webPageUrl=currentPage.cached_page.url.toLowerCase();
          }
 else {
            webPageUrl=currentPage.url.toLowerCase();
          }
          try {
            anchor=URLDecoder.decode(url.substring(index + 1),"UTF-8");
          }
 catch (          Exception ignore) {
            anchor="";
          }
          if (url.toLowerCase().contains(webPageUrl)) {
            if (TextUtils.isEmpty(anchor)) {
              layoutManager[0].scrollToPositionWithOffset(0,0);
              checkScrollAnimated();
            }
 else {
              scrollToAnchor(anchor);
            }
            isAnchor=true;
          }
        }
 else {
          anchor=null;
        }
        if (!isAnchor) {
          openWebpageUrl(pressedLink.getUrl(),anchor);
        }
      }
    }
  }
 else   if (event.getAction() == MotionEvent.ACTION_CANCEL && (popupWindow == null || !popupWindow.isShowing())) {
    removeLink=true;
  }
  if (removeLink) {
    removePressedLink();
  }
  if (event.getAction() == MotionEvent.ACTION_DOWN) {
    startCheckLongPress();
  }
  if (event.getAction() != MotionEvent.ACTION_DOWN && event.getAction() != MotionEvent.ACTION_MOVE) {
    cancelCheckLongPress();
  }
  if (parentView instanceof BlockDetailsCell) {
    return pressedLink != null;
  }
 else {
    return pressedLinkOwnerLayout != null;
  }
}
