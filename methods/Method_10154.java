/** 
 * Converts the specified content with emotions. Replaces the emoji's alias by its unicode. Example: ":smile:" gives "?".
 * @param content the specified content
 * @return converted content
 */
public static String convert(final String content){
  String ret=content;
  if (!EMOJI_PATTERN.matcher(ret).find()) {
    return ret;
  }
  ret=toUnicode(ret);
  for (  final String emojiCode : EMOJIS) {
    String repl="<img alt=\"" + emojiCode + "\" class=\"emoji\" width=\"18\" src=\"" + Latkes.getStaticServePath() + "/emoji/graphics/" + emojiCode;
    final String suffix="huaji".equals(emojiCode) ? ".gif" : ".png";
    repl+=suffix + "\" title=\"" + emojiCode + "\" />";
    ret=ret.replace(":" + emojiCode + ":",repl);
  }
  return ret;
}
