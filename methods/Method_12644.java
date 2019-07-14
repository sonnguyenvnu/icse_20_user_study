/** 
 * ????????
 * @param charArray
 * @param begin
 * @param length
 * @param enabled
 */
private synchronized void fillSegment(char[] charArray,int begin,int length,int enabled){
  Character beginChar=Character.valueOf(charArray[begin]);
  Character keyChar=charMap.get(beginChar);
  if (keyChar == null) {
    charMap.put(beginChar,beginChar);
    keyChar=beginChar;
  }
  DictSegment ds=lookforSegment(keyChar,enabled);
  if (ds != null) {
    if (length > 1) {
      ds.fillSegment(charArray,begin + 1,length - 1,enabled);
    }
 else     if (length == 1) {
      ds.nodeState=enabled;
    }
  }
}
