private boolean checkTextBlockMotionEvent(MotionEvent event){
  if (currentMessageObject.type != 0 || currentMessageObject.textLayoutBlocks == null || currentMessageObject.textLayoutBlocks.isEmpty() || !(currentMessageObject.messageText instanceof Spannable)) {
    return false;
  }
  if (event.getAction() == MotionEvent.ACTION_DOWN || event.getAction() == MotionEvent.ACTION_UP && pressedLinkType == 1) {
    int x=(int)event.getX();
    int y=(int)event.getY();
    if (x >= textX && y >= textY && x <= textX + currentMessageObject.textWidth && y <= textY + currentMessageObject.textHeight) {
      y-=textY;
      int blockNum=0;
      for (int a=0; a < currentMessageObject.textLayoutBlocks.size(); a++) {
        if (currentMessageObject.textLayoutBlocks.get(a).textYOffset > y) {
          break;
        }
        blockNum=a;
      }
      try {
        MessageObject.TextLayoutBlock block=currentMessageObject.textLayoutBlocks.get(blockNum);
        x-=textX - (block.isRtl() ? currentMessageObject.textXOffset : 0);
        y-=block.textYOffset;
        final int line=block.textLayout.getLineForVertical(y);
        final int off=block.textLayout.getOffsetForHorizontal(line,x);
        final float left=block.textLayout.getLineLeft(line);
        if (left <= x && left + block.textLayout.getLineWidth(line) >= x) {
          Spannable buffer=(Spannable)currentMessageObject.messageText;
          CharacterStyle[] link=buffer.getSpans(off,off,ClickableSpan.class);
          boolean isMono=false;
          if (link == null || link.length == 0) {
            link=buffer.getSpans(off,off,URLSpanMono.class);
            isMono=true;
          }
          boolean ignore=false;
          if (link.length == 0 || link.length != 0 && link[0] instanceof URLSpanBotCommand && !URLSpanBotCommand.enabled) {
            ignore=true;
          }
          if (!ignore) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
              pressedLink=link[0];
              linkBlockNum=blockNum;
              pressedLinkType=1;
              resetUrlPaths(false);
              try {
                LinkPath path=obtainNewUrlPath(false);
                int start=buffer.getSpanStart(pressedLink);
                int end=buffer.getSpanEnd(pressedLink);
                path.setCurrentLayout(block.textLayout,start,0);
                block.textLayout.getSelectionPath(start,end,path);
                if (end >= block.charactersEnd) {
                  for (int a=blockNum + 1; a < currentMessageObject.textLayoutBlocks.size(); a++) {
                    MessageObject.TextLayoutBlock nextBlock=currentMessageObject.textLayoutBlocks.get(a);
                    CharacterStyle[] nextLink;
                    if (isMono) {
                      nextLink=buffer.getSpans(nextBlock.charactersOffset,nextBlock.charactersOffset,URLSpanMono.class);
                    }
 else {
                      nextLink=buffer.getSpans(nextBlock.charactersOffset,nextBlock.charactersOffset,ClickableSpan.class);
                    }
                    if (nextLink == null || nextLink.length == 0 || nextLink[0] != pressedLink) {
                      break;
                    }
                    path=obtainNewUrlPath(false);
                    path.setCurrentLayout(nextBlock.textLayout,0,nextBlock.textYOffset - block.textYOffset);
                    nextBlock.textLayout.getSelectionPath(0,end,path);
                    if (end < nextBlock.charactersEnd - 1) {
                      break;
                    }
                  }
                }
                if (start <= block.charactersOffset) {
                  int offsetY=0;
                  for (int a=blockNum - 1; a >= 0; a--) {
                    MessageObject.TextLayoutBlock nextBlock=currentMessageObject.textLayoutBlocks.get(a);
                    CharacterStyle[] nextLink;
                    if (isMono) {
                      nextLink=buffer.getSpans(nextBlock.charactersEnd - 1,nextBlock.charactersEnd - 1,URLSpanMono.class);
                    }
 else {
                      nextLink=buffer.getSpans(nextBlock.charactersEnd - 1,nextBlock.charactersEnd - 1,ClickableSpan.class);
                    }
                    if (nextLink == null || nextLink.length == 0 || nextLink[0] != pressedLink) {
                      break;
                    }
                    path=obtainNewUrlPath(false);
                    start=buffer.getSpanStart(pressedLink);
                    offsetY-=nextBlock.height;
                    path.setCurrentLayout(nextBlock.textLayout,start,offsetY);
                    nextBlock.textLayout.getSelectionPath(start,buffer.getSpanEnd(pressedLink),path);
                    if (start > nextBlock.charactersOffset) {
                      break;
                    }
                  }
                }
              }
 catch (              Exception e) {
                FileLog.e(e);
              }
              invalidate();
              return true;
            }
 else {
              if (link[0] == pressedLink) {
                delegate.didPressUrl(currentMessageObject,pressedLink,false);
                resetPressedLink(1);
                return true;
              }
            }
          }
        }
      }
 catch (      Exception e) {
        FileLog.e(e);
      }
    }
 else {
      resetPressedLink(1);
    }
  }
  return false;
}
