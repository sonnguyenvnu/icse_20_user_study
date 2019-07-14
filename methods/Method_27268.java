public static boolean isOnlyEmojis(String string){
  return string != null && EmojiParser.removeAllEmojis(string).isEmpty();
}
