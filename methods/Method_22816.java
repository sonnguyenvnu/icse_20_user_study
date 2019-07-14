/** 
 * Adds a key-value mapping.
 * @param keyword The key
 * @param id The value
 */
public void add(String keyword,byte id,boolean paren){
  int key=getStringMapKey(keyword);
  Keyword[] map=paren ? parenMap : literalMap;
  map[key]=new Keyword(keyword.toCharArray(),id,map[key]);
}
