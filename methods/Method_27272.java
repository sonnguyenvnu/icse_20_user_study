/** 
 * Replaces the emoji's aliases (between 2 ':') occurrences and the html representations by their unicode.<br> Examples:<br> <code>:smile:</code> will be replaced by <code>?</code><br> <code>&amp;#128516;</code> will be replaced by <code>?</code><br> <code>:boy|type_6:</code> will be replaced by <code>??</code>
 * @param input the string to parse
 * @return the string with the aliases and html representations replaced bytheir unicode.
 */
public static String parseToUnicode(String input){
  List<AliasCandidate> candidates=getAliasCandidates(input);
  String result=input;
  for (  AliasCandidate candidate : candidates) {
    Emoji emoji=EmojiManager.getForAlias(candidate.alias);
    if (emoji != null) {
      if (emoji.supportsFitzpatrick() || (!emoji.supportsFitzpatrick() && candidate.fitzpatrick == null)) {
        String replacement=emoji.getUnicode();
        if (candidate.fitzpatrick != null) {
          replacement+=candidate.fitzpatrick.unicode;
        }
        result=result.replace(":" + candidate.fullString + ":",replacement);
      }
    }
  }
  for (  Emoji emoji : EmojiManager.getAll()) {
    result=result.replace(emoji.getHtmlHexadecimal(),emoji.getUnicode());
    result=result.replace(emoji.getHtmlDecimal(),emoji.getUnicode());
  }
  return result;
}
