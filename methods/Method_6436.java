public static boolean isValidEmoji(String code){
  DrawableInfo info=rects.get(code);
  if (info == null) {
    CharSequence newCode=EmojiData.emojiAliasMap.get(code);
    if (newCode != null) {
      info=Emoji.rects.get(newCode);
    }
  }
  return info != null;
}
