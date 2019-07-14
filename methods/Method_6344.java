public String getEmojiForSticker(long id){
  String value=stickersByEmoji.get(id);
  return value != null ? value : "";
}
