/** 
 * Find end of tag (&gt;). The position returned is the position of the &gt; plus one (exclusive).
 * @param markup The WebVTT cue markup to be parsed.
 * @param startPos The position from where to start searching for the end of tag.
 * @return The position of the end of tag plus 1 (one).
 */
private static int findEndOfTag(String markup,int startPos){
  int index=markup.indexOf(CHAR_GREATER_THAN,startPos);
  return index == -1 ? markup.length() : index + 1;
}
