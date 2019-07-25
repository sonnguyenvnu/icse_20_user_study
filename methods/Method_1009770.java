public boolean remove(@Nullable Sticker sticker){
  if (stickers.contains(sticker)) {
    stickers.remove(sticker);
    if (onStickerOperationListener != null) {
      onStickerOperationListener.onStickerDeleted(sticker);
    }
    if (handlingSticker == sticker) {
      handlingSticker=null;
    }
    invalidate();
    return true;
  }
 else {
    Log.d(TAG,"remove: the sticker is not in this StickerView");
    return false;
  }
}
