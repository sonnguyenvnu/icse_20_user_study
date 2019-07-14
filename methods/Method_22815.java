/** 
 * Looks up a key.
 * @param text The text segment
 * @param offset The offset of the substring within the text segment
 * @param length The length of the substring
 */
public byte lookup(Segment text,int offset,int length,boolean paren){
  if (length == 0) {
    return Token.NULL;
  }
  int key=getSegmentMapKey(text,offset,length);
  Keyword k=paren ? parenMap[key] : literalMap[key];
  while (k != null) {
    if (length == k.keyword.length) {
      if (regionMatches(ignoreCase,text,offset,k.keyword)) {
        return k.id;
      }
    }
    k=k.next;
  }
  return Token.NULL;
}
