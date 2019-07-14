/** 
 * Removes a set of emojis from a String
 * @param str            the string to process
 * @param emojisToRemove the emojis to remove from this string
 * @return the string without the emojis that were removed
 */
public static String removeEmojis(String str,final Collection<Emoji> emojisToRemove){
  EmojiTransformer emojiTransformer=unicodeCandidate -> {
    if (!emojisToRemove.contains(unicodeCandidate.getEmoji())) {
      return unicodeCandidate.getEmoji().getUnicode() + unicodeCandidate.getFitzpatrickUnicode();
    }
    return "";
  }
;
  return parseFromUnicode(str,emojiTransformer);
}
