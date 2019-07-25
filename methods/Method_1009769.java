public boolean replace(@Nullable Sticker sticker,boolean needStayState){
  if (handlingSticker != null && sticker != null) {
    float width=getWidth();
    float height=getHeight();
    if (needStayState) {
      sticker.setMatrix(handlingSticker.getMatrix());
      sticker.setFlippedVertically(handlingSticker.isFlippedVertically());
      sticker.setFlippedHorizontally(handlingSticker.isFlippedHorizontally());
    }
 else {
      handlingSticker.getMatrix().reset();
      float offsetX=(width - handlingSticker.getWidth()) / 2f;
      float offsetY=(height - handlingSticker.getHeight()) / 2f;
      sticker.getMatrix().postTranslate(offsetX,offsetY);
      float scaleFactor;
      if (width < height) {
        scaleFactor=width / handlingSticker.getDrawable().getIntrinsicWidth();
      }
 else {
        scaleFactor=height / handlingSticker.getDrawable().getIntrinsicHeight();
      }
      sticker.getMatrix().postScale(scaleFactor / 2f,scaleFactor / 2f,width / 2f,height / 2f);
    }
    int index=stickers.indexOf(handlingSticker);
    stickers.set(index,sticker);
    handlingSticker=sticker;
    invalidate();
    return true;
  }
 else {
    return false;
  }
}
