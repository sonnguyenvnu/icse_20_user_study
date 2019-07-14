public boolean onTouch(MotionEvent event,final RecyclerListView listView,final int height,final Object listener,ContentPreviewViewerDelegate contentPreviewViewerDelegate){
  delegate=contentPreviewViewerDelegate;
  if (openPreviewRunnable != null || isVisible()) {
    if (event.getAction() == MotionEvent.ACTION_UP || event.getAction() == MotionEvent.ACTION_CANCEL || event.getAction() == MotionEvent.ACTION_POINTER_UP) {
      AndroidUtilities.runOnUIThread(() -> {
        if (listView instanceof RecyclerListView) {
          listView.setOnItemClickListener((RecyclerListView.OnItemClickListener)listener);
        }
      }
,150);
      if (openPreviewRunnable != null) {
        AndroidUtilities.cancelRunOnUIThread(openPreviewRunnable);
        openPreviewRunnable=null;
      }
 else       if (isVisible()) {
        close();
        if (currentPreviewCell != null) {
          if (currentPreviewCell instanceof StickerEmojiCell) {
            ((StickerEmojiCell)currentPreviewCell).setScaled(false);
          }
 else           if (currentPreviewCell instanceof StickerCell) {
            ((StickerCell)currentPreviewCell).setScaled(false);
          }
 else           if (currentPreviewCell instanceof ContextLinkCell) {
            ((ContextLinkCell)currentPreviewCell).setScaled(false);
          }
          currentPreviewCell=null;
        }
      }
    }
 else     if (event.getAction() != MotionEvent.ACTION_DOWN) {
      if (isVisible) {
        if (event.getAction() == MotionEvent.ACTION_MOVE) {
          if (currentContentType == CONTENT_TYPE_GIF) {
            if (visibleDialog == null && showProgress == 1.0f) {
              if (lastTouchY == -10000) {
                lastTouchY=event.getY();
                currentMoveY=0;
                moveY=0;
              }
 else {
                float newY=event.getY();
                currentMoveY+=newY - lastTouchY;
                lastTouchY=newY;
                if (currentMoveY > 0) {
                  currentMoveY=0;
                }
 else                 if (currentMoveY < -AndroidUtilities.dp(60)) {
                  currentMoveY=-AndroidUtilities.dp(60);
                }
                moveY=rubberYPoisition(currentMoveY,AndroidUtilities.dp(200));
                containerView.invalidate();
                if (currentMoveY <= -AndroidUtilities.dp(55)) {
                  AndroidUtilities.cancelRunOnUIThread(showSheetRunnable);
                  showSheetRunnable.run();
                  return true;
                }
              }
            }
            return true;
          }
          int x=(int)event.getX();
          int y=(int)event.getY();
          int count=listView.getChildCount();
          for (int a=0; a < count; a++) {
            View view=null;
            if (listView instanceof RecyclerListView) {
              view=listView.getChildAt(a);
            }
            if (view == null) {
              return false;
            }
            int top=view.getTop();
            int bottom=view.getBottom();
            int left=view.getLeft();
            int right=view.getRight();
            if (top > y || bottom < y || left > x || right < x) {
              continue;
            }
            int contentType=CONTENT_TYPE_NONE;
            if (view instanceof StickerEmojiCell) {
              contentType=CONTENT_TYPE_STICKER;
              centerImage.setRoundRadius(0);
            }
 else             if (view instanceof StickerCell) {
              contentType=CONTENT_TYPE_STICKER;
              centerImage.setRoundRadius(0);
            }
 else             if (view instanceof ContextLinkCell) {
              ContextLinkCell cell=(ContextLinkCell)view;
              if (cell.isSticker()) {
                contentType=CONTENT_TYPE_STICKER;
                centerImage.setRoundRadius(0);
              }
 else               if (cell.isGif()) {
                contentType=CONTENT_TYPE_GIF;
                centerImage.setRoundRadius(AndroidUtilities.dp(6));
              }
            }
            if (contentType == CONTENT_TYPE_NONE || view == currentPreviewCell) {
              break;
            }
            if (currentPreviewCell instanceof StickerEmojiCell) {
              ((StickerEmojiCell)currentPreviewCell).setScaled(false);
            }
 else             if (currentPreviewCell instanceof StickerCell) {
              ((StickerCell)currentPreviewCell).setScaled(false);
            }
 else             if (currentPreviewCell instanceof ContextLinkCell) {
              ((ContextLinkCell)currentPreviewCell).setScaled(false);
            }
            currentPreviewCell=view;
            setKeyboardHeight(height);
            clearsInputField=false;
            if (currentPreviewCell instanceof StickerEmojiCell) {
              StickerEmojiCell stickerEmojiCell=(StickerEmojiCell)currentPreviewCell;
              open(stickerEmojiCell.getSticker(),null,contentType,((StickerEmojiCell)currentPreviewCell).isRecent());
              stickerEmojiCell.setScaled(true);
            }
 else             if (currentPreviewCell instanceof StickerCell) {
              StickerCell stickerCell=(StickerCell)currentPreviewCell;
              open(stickerCell.getSticker(),null,contentType,false);
              stickerCell.setScaled(true);
              clearsInputField=stickerCell.isClearsInputField();
            }
 else             if (currentPreviewCell instanceof ContextLinkCell) {
              ContextLinkCell contextLinkCell=(ContextLinkCell)currentPreviewCell;
              open(contextLinkCell.getDocument(),contextLinkCell.getBotInlineResult(),contentType,false);
              if (contentType != CONTENT_TYPE_GIF) {
                contextLinkCell.setScaled(true);
              }
            }
            return true;
          }
        }
        return true;
      }
 else       if (openPreviewRunnable != null) {
        if (event.getAction() == MotionEvent.ACTION_MOVE) {
          if (Math.hypot(startX - event.getX(),startY - event.getY()) > AndroidUtilities.dp(10)) {
            AndroidUtilities.cancelRunOnUIThread(openPreviewRunnable);
            openPreviewRunnable=null;
          }
        }
 else {
          AndroidUtilities.cancelRunOnUIThread(openPreviewRunnable);
          openPreviewRunnable=null;
        }
      }
    }
  }
  return false;
}
