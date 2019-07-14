public boolean isAnimatedSticker(){
  if (type != 1000) {
    return type == TYPE_ANIMATED_STICKER;
  }
  return isAnimatedStickerMessage(messageOwner);
}
