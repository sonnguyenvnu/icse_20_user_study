/** 
 * Replaces the emoji's unicode occurrences by one of their alias (between 2 ':').<br> Example: <code>?</code> will be replaced by <code>:smile:</code><br> <br> When a fitzpatrick modifier is present with a PARSE action, a "|" will be appendend to the alias, with the fitzpatrick type.<br> Example: <code>??</code> will be replaced by <code>:boy|type_6:</code><br> The fitzpatrick types are: type_1_2, type_3, type_4, type_5, type_6<br> <br> When a fitzpatrick modifier is present with a REMOVE action, the modifier will be deleted.<br> Example: <code>??</code> will be replaced by <code>:boy:</code><br> <br> When a fitzpatrick modifier is present with a IGNORE action, the modifier will be ignored.<br> Example: <code>??</code> will be replaced by <code>:boy:?</code><br>
 * @param input             the string to parse
 * @param fitzpatrickAction the action to apply for the fitzpatrick modifiers
 * @return the string with the emojis replaced by their alias.
 */
private static String parseToAliases(String input,final FitzpatrickAction fitzpatrickAction){
  EmojiTransformer emojiTransformer=unicodeCandidate -> {
switch (fitzpatrickAction) {
default :
case PARSE:
      if (unicodeCandidate.hasFitzpatrick()) {
        return ":" + unicodeCandidate.getEmoji().getAliases().get(0) + "|" + unicodeCandidate.getFitzpatrickType() + ":";
      }
case REMOVE:
    return ":" + unicodeCandidate.getEmoji().getAliases().get(0) + ":";
case IGNORE:
  return ":" + unicodeCandidate.getEmoji().getAliases().get(0) + ":" + unicodeCandidate.getFitzpatrickUnicode();
}
}
;
return parseFromUnicode(input,emojiTransformer);
}
