/** 
 * Returns end index of a unicode emoji if it is found in text starting at index startPos, -1 if not found. This returns the longest matching emoji, for example, in "\uD83D\uDC68\u200D\uD83D\uDC69\u200D\uD83D\uDC66" it will find alias:family_man_woman_boy, NOT alias:man
 * @param text the current text where we are looking for an emoji
 * @param startPos the position in the text where we should start looking foran emoji end
 * @return the end index of the unicode emoji starting at startPos. -1 if notfound
 */
private static int getEmojiEndPos(char[] text,int startPos){
  int best=-1;
  for (int j=startPos + 1; j <= text.length; j++) {
    EmojiTrie.Matches status=EmojiManager.isEmoji(Arrays.copyOfRange(text,startPos,j));
    if (status.exactMatch()) {
      best=j;
    }
 else     if (status.impossibleMatch()) {
      return best;
    }
  }
  return best;
}
