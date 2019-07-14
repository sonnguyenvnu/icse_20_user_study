/** 
 * ????????
 * @param key ?
 * @return ?????
 * @deprecated ???????
 */
public LinkedList<Map.Entry<String,V>> commonPrefixSearchWithValue(String key){
  int len=key.length();
  LinkedList<Map.Entry<String,V>> result=new LinkedList<Map.Entry<String,V>>();
  char[] keyChars=key.toCharArray();
  int b=base[0];
  int n;
  int p;
  for (int i=0; i < len; ++i) {
    p=b;
    n=base[p];
    if (b == check[p] && n < 0) {
      result.add(new AbstractMap.SimpleEntry<String,V>(new String(keyChars,0,i),v[-n - 1]));
    }
    p=b + (int)(keyChars[i]) + 1;
    if (b == check[p])     b=base[p];
 else     return result;
  }
  p=b;
  n=base[p];
  if (b == check[p] && n < 0) {
    result.add(new AbstractMap.SimpleEntry<String,V>(key,v[-n - 1]));
  }
  return result;
}
