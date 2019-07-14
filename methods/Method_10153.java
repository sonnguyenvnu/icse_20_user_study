/** 
 * Clears the emotions ( {@literal [em00], :heart:}) with specified content.
 * @param content the specified content
 * @return cleared content
 */
public static String clear(final String content){
  String ret=content.replaceAll("\\[em\\d+]","");
  for (  final String emojiCode : EMOJIS) {
    final String emoji=":" + emojiCode + ":";
    ret=ret.replace(emoji,"");
  }
  return ret;
}
