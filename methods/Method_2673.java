/** 
 * ????????????????
 * @param keyChars
 * @param begin
 * @return
 */
public LinkedList<Map.Entry<String,V>> commonPrefixSearchWithValue(char[] keyChars,int begin){
  int len=keyChars.length;
  LinkedList<Map.Entry<String,V>> result=new LinkedList<Map.Entry<String,V>>();
  int b=base[0];
  int n;
  int p;
  for (int i=begin; i < len; ++i) {
    p=b;
    n=base[p];
    if (b == check[p] && n < 0) {
      result.add(new AbstractMap.SimpleEntry<String,V>(new String(keyChars,begin,i - begin),v[-n - 1]));
    }
    p=b + (int)(keyChars[i]) + 1;
    if (b == check[p])     b=base[p];
 else     return result;
  }
  p=b;
  n=base[p];
  if (b == check[p] && n < 0) {
    result.add(new AbstractMap.SimpleEntry<String,V>(new String(keyChars,begin,len - begin),v[-n - 1]));
  }
  return result;
}
