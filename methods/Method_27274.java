/** 
 * Removes all emojis from a String
 * @param str the string to process
 * @return the string without any emoji
 */
public static String removeAllEmojis(String str){
  EmojiTransformer emojiTransformer=unicodeCandidate -> "";
  return parseFromUnicode(str,emojiTransformer);
}
