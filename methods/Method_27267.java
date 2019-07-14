public static Emoji getByUnicode(String unicode){
  if (unicode == null) {
    return null;
  }
  return EMOJI_TRIE.getEmoji(unicode);
}
