/** 
 * Checks whether the specified content contains reserved words.
 * @param content the specified content
 * @return {@code true} if it contains reserved words, returns {@code false} otherwise
 */
public boolean containReservedWord(final String content){
  if (StringUtils.isBlank(content)) {
    return false;
  }
  try {
    final List<JSONObject> reservedWords=getReservedWords();
    for (    final JSONObject reservedWord : reservedWords) {
      if (content.contains(reservedWord.optString(Option.OPTION_VALUE))) {
        return true;
      }
    }
    return false;
  }
 catch (  final Exception e) {
    return true;
  }
}
