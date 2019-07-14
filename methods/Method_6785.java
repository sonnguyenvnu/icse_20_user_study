public boolean shouldDrawWithoutBackground(){
  return type == TYPE_STICKER || type == TYPE_ANIMATED_STICKER || type == TYPE_ROUND_VIDEO;
}
