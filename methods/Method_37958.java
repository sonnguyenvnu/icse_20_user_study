/** 
 * Calculates indexedTextName (collection[*]) if applicable.
 */
private String calcIndexKey(final String key){
  String indexedKey=null;
  if (key.indexOf('[') != -1) {
    int i=-1;
    indexedKey=key;
    while ((i=indexedKey.indexOf('[',i + 1)) != -1) {
      int j=indexedKey.indexOf(']',i);
      String a=indexedKey.substring(0,i);
      String b=indexedKey.substring(j);
      indexedKey=a + "[*" + b;
    }
  }
  return indexedKey;
}
