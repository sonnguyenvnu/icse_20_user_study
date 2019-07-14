public void reset(){
  if (openPreviewRunnable != null) {
    AndroidUtilities.cancelRunOnUIThread(openPreviewRunnable);
    openPreviewRunnable=null;
  }
  if (currentPreviewCell != null) {
    if (currentPreviewCell instanceof StickerEmojiCell) {
      ((StickerEmojiCell)currentPreviewCell).setScaled(false);
    }
 else     if (currentPreviewCell instanceof StickerCell) {
      ((StickerCell)currentPreviewCell).setScaled(false);
    }
 else     if (currentPreviewCell instanceof ContextLinkCell) {
      ((ContextLinkCell)currentPreviewCell).setScaled(false);
    }
    currentPreviewCell=null;
  }
}
