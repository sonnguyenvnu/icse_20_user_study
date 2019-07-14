public boolean onInterceptTouchEvent(MotionEvent event,final RecyclerListView listView,final int height,ContentPreviewViewerDelegate contentPreviewViewerDelegate){
  delegate=contentPreviewViewerDelegate;
  if (event.getAction() == MotionEvent.ACTION_DOWN) {
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
        if (((StickerEmojiCell)view).showingBitmap()) {
          contentType=CONTENT_TYPE_STICKER;
          centerImage.setRoundRadius(0);
        }
      }
 else       if (view instanceof StickerCell) {
        if (((StickerCell)view).showingBitmap()) {
          contentType=CONTENT_TYPE_STICKER;
          centerImage.setRoundRadius(0);
        }
      }
 else       if (view instanceof ContextLinkCell) {
        ContextLinkCell cell=(ContextLinkCell)view;
        if (cell.showingBitmap()) {
          if (cell.isSticker()) {
            contentType=CONTENT_TYPE_STICKER;
            centerImage.setRoundRadius(0);
          }
 else           if (cell.isGif()) {
            contentType=CONTENT_TYPE_GIF;
            centerImage.setRoundRadius(AndroidUtilities.dp(6));
          }
        }
      }
      if (contentType == CONTENT_TYPE_NONE) {
        return false;
      }
      startX=x;
      startY=y;
      currentPreviewCell=view;
      int contentTypeFinal=contentType;
      openPreviewRunnable=() -> {
        if (openPreviewRunnable == null) {
          return;
        }
        listView.setOnItemClickListener((RecyclerListView.OnItemClickListener)null);
        listView.requestDisallowInterceptTouchEvent(true);
        openPreviewRunnable=null;
        setParentActivity((Activity)listView.getContext());
        setKeyboardHeight(height);
        clearsInputField=false;
        if (currentPreviewCell instanceof StickerEmojiCell) {
          StickerEmojiCell stickerEmojiCell=(StickerEmojiCell)currentPreviewCell;
          open(stickerEmojiCell.getSticker(),null,contentTypeFinal,((StickerEmojiCell)currentPreviewCell).isRecent());
          stickerEmojiCell.setScaled(true);
        }
 else         if (currentPreviewCell instanceof StickerCell) {
          StickerCell stickerCell=(StickerCell)currentPreviewCell;
          open(stickerCell.getSticker(),null,contentTypeFinal,false);
          stickerCell.setScaled(true);
          clearsInputField=stickerCell.isClearsInputField();
        }
 else         if (currentPreviewCell instanceof ContextLinkCell) {
          ContextLinkCell contextLinkCell=(ContextLinkCell)currentPreviewCell;
          open(contextLinkCell.getDocument(),contextLinkCell.getBotInlineResult(),contentTypeFinal,false);
          if (contentTypeFinal != CONTENT_TYPE_GIF) {
            contextLinkCell.setScaled(true);
          }
        }
      }
;
      AndroidUtilities.runOnUIThread(openPreviewRunnable,200);
      return true;
    }
  }
  return false;
}
